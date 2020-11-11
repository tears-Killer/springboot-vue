<template>
    <div>
        <div class="crop-demo">
            <img :src="cropImg" class="pre-img">
            <div class="crop-demo-btn">更换头像
                <input class="crop-input" type="file" name="image" accept="image/*" @change="setImage" />
            </div>
            <div class="card">
                <p>用户名：<span>{{user.account}}</span></p>
                <p>职位：<span>{{role}}</span></p>
                <!-- <em>修改密码?</em> -->
            </div>
        </div>
        <div class="container">
            <div class="form-box">
                <el-tag class="infotag">基本资料</el-tag>
                <el-form label-width="80px">
                    <el-form-item class="label" label="用户名:">
                        <el-input class="ipt" v-model="user.account"></el-input>
                    </el-form-item>
                    <el-form-item class="label" label="姓名:">
                        <el-input class="ipt" v-model="user.employee.employeeName"></el-input>
                    </el-form-item>
                    <el-form-item class="label" label="性别:">
                        <el-select class="ipt" v-model="user.employee.employeeSex" placeholder="请选择">
                            <el-option label="女" :value="0"> </el-option>
                            <el-option label="男" :value="1"> </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item class="label" label="年龄:">
                        <el-input class="ipt" v-model="user.employee.employeeAge"></el-input>
                    </el-form-item>
                    <el-form-item class="label" label="手机号:">
                        <el-input class="ipt" v-model="user.employee.employeePhone"></el-input>
                    </el-form-item>
                    <el-form-item v-if="user.employee.employeeRole!=2" class="label" label="职位:">
                        <el-selectclass="ipt" v-model="user.employee.employeeRole" placeholder="请选择">
                            <el-option label="普通员工" :value="0"> </el-option>
                            <el-option label="经理" :value="1"> </el-option>
                        </el-selectclass=>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" class="editbtn" @click="editUser">确认修改</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
        <el-dialog title="裁剪图片" :visible.sync="dialogVisible" width="30%">
            <vue-cropper ref='cropper' :src="imgSrc" :ready="cropImage" :zoom="cropImage" :cropmove="cropImage"
                style="width:100%;height:300px;"></vue-cropper>
            <span slot="footer" class="dialog-footer">
                <el-button @click="cancelCrop">取 消</el-button>
                <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    import VueCropper from 'vue-cropperjs';
    export default {
        data() {
            return {
                defaultSrc: require('../../assets/img/img.jpg'),
                fileList: [],
                imgSrc: '',
                cropImg: '',
                dialogVisible: false,
                user: {}
            };
        },
        components: {
            VueCropper
        },
        computed: {
            role() {
                let role = this.user.employee.employeeRole
                if (role == 0) {
                    return '普通员工'
                } else if (role == 1) {
                    return '经理'
                } else {
                    return '老板'
                }
            }
        },
        methods: {
            getUser() {
                this.user = this.$store.state.user
            },
            editUser(){
                if(this.user.employee.employeeRole == null){
                    this.user.employee.employeeRole = 2
                }
                
                this.$axios.post('/user/update',this.user).then(res => {
                    this.$message.success('修改成功')
                })
            },
            setImage(e) {
                const file = e.target.files[0];
                if (!file.type.includes('image/')) {
                    return;
                }
                const reader = new FileReader();
                reader.onload = (event) => {
                    this.dialogVisible = true;
                    this.imgSrc = event.target.result;
                    this.$refs.cropper && this.$refs.cropper.replace(event.target.result);
                };
                reader.readAsDataURL(file);
            },
            cropImage() {
                this.cropImg = this.$refs.cropper.getCroppedCanvas().toDataURL();
            },
            cancelCrop() {
                this.dialogVisible = false;
                this.cropImg = this.defaultSrc;
            },
            handleError() {
                this.$notify.error({
                    title: '上传失败',
                    message: '图片上传接口上传失败，可更改为自己的服务器接口'
                });
            }
        },
        created() {
            //在页面刷新时将vuex里的信息保存到localStorage里
            window.addEventListener("beforeunload", () => {
                localStorage.setItem("messageStore", JSON.stringify(this.$store.state))
            })
            //在页面加载时读取localStorage里的状态信息
            localStorage.getItem("messageStore") && this.$store.replaceState(Object.assign(this.$store.state, JSON.parse(localStorage.getItem("messageStore"))));
            this.cropImg = this.defaultSrc;
            this.getUser()
        }
    };
</script>

<style scoped>
    .pre-img {
        margin: 20px 30px 10px;
        width: 150px;
        height: 150px;
        background: #f8f8f8;
        border: 1px solid black;
        border-radius: 50%;
    }

    .crop-demo {
        padding-bottom: 10px;
        position: relative;
        margin-left: 20px;
        margin-bottom: 20px;
        width: 500px;
        border-radius: 20px;
        box-shadow: 0 0 10px black;
        background-color: white;
    }

    .crop-demo-btn {
        position: relative;
        width: 100px;
        height: 40px;
        line-height: 40px;
        padding: 0 20px;
        margin-left: 60px;
        margin-bottom: 10px;
        background-color: #409eff;
        color: #fff;
        font-size: 14px;
        border-radius: 10px;
        box-sizing: border-box;
        cursor: pointer;
    }

    .crop-input {
        position: absolute;
        width: 100px;
        height: 40px;
        left: 0;
        top: 0;
        opacity: 0;
        cursor: pointer;
    }

    .card {
        position: absolute;
        top: 80px;
        right: 160px;
    }

    .card p {
        margin-bottom: 10px;
        font-size: 18px;
        font-weight: 600;
    }

    .card em {
        font-size: 14px;
        color: blue;
        cursor: pointer;
    }

    .infotag {
        margin-bottom: 10px;
    }

    .container {
        width: 700px;
        box-shadow: 0 0 2px gray;
        font-weight: 600;
    }

    .label {
        margin-left: 80px;
    }

    .ipt {
        width: 200px;
        margin-left: 20px;
    }

    .editbtn {
        margin-left: 100px;
    }
</style>