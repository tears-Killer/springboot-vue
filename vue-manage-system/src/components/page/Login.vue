<template>
    <div class="login-wrap">
        <div class="ms-login">
            <div class="ms-title">后台管理系统</div>
            <el-form :model="param" :rules="rules" ref="login" label-width="0px" class="ms-content">
                <el-form-item prop="account">
                    <el-input v-model="param.account" placeholder="account">
                        <el-button slot="prepend" icon="el-icon-lx-people"></el-button>
                    </el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input type="password" placeholder="password" v-model="param.password" @keyup.enter.native="submitForm()">
                        <el-button slot="prepend" icon="el-icon-lx-lock"></el-button>
                    </el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="checkLogin">登录</el-button>
                </div>
                <p class="login-tips">{{ msg }}</p>
            </el-form>
        </div>
    </div>
</template>

<script>
export default {
    data: function () {
        return {
            param: {
                account: '',
                password: ''
            },
            rules: {
                account: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
                password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
            },
            msg: ''
        };
    },
    methods: {
        checkLogin() {
            let data = this.$qs.stringify({
                account: this.param.account,
                password: this.param.password
            });

            this.$axios('user/login', {
                method: 'post',
                data
            }).then((res) => {
                this.msg = res.data.msg;
                if (res.data.user != null) {
                    this.$router.push({
                        path: res.data.url,
                        params:{
                            user: res.data.user
                        }
                    });
                    this.$store.commit('getUser',res.data.user)
                }
            });
        }
    }
};
</script>

<style scoped>
.login-wrap {
    position: relative;
    width: 100%;
    height: 100%;
    background-image: url(../../assets/img/login.jpg);
    background-size: 100% 100%;
}

.ms-title {
    width: 100%;
    line-height: 50px;
    text-align: center;
    font-size: 20px;
    color: #fff;
    border-bottom: 1px solid #ddd;
}

.ms-login {
    position: absolute;
    left: 50%;
    top: 50%;
    width: 350px;
    margin: -190px 0 0 -175px;
    border-radius: 5px;
    background: rgba(255, 255, 255, 0.2);
    overflow: hidden;
}

.ms-content {
    padding: 30px 30px;
    text-align: center;
}

.login-btn {
    text-align: center;
}

.login-btn button {
    width: 100%;
    height: 36px;
    margin-bottom: 10px;
}
.login-tips {
    font-size: 14px;
    line-height: 30px;
    color: blue;
}
</style>