<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 商品管理
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-button type="primary" icon="el-icon-circle-plus-outline" @click="handleAdd">增加商品</el-button>
                <el-input v-model="query.name" placeholder="商品名" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索商品</el-button>
            </div>
            <el-table :data="tableData" border class="table" ref="multipleTable" header-cell-class-name="table-header"
                @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="55" align="center"></el-table-column>
                <el-table-column prop="shopName" label="商品名"></el-table-column>
                <el-table-column prop="shopPrice" label="价格"></el-table-column>
                <el-table-column prop="shopInfo" label="商品信息"></el-table-column>
                <el-table-column label="商品状态">
                    <template slot-scope="scope">
                        <el-tag>{{scope.row.shopState == 0 ? '售完':'可售'}}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">编辑
                        </el-button>
                        <el-button type="text" icon="el-icon-delete" class="red"
                            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination background layout="total, prev, pager, next" :current-page="query.pageIndex"
                    :page-size="query.pageSize" :total="pageTotal" @current-change="handlePageChange"></el-pagination>
            </div>
        </div>

        <!-- 修改弹出框 -->
        <el-dialog title="修改信息" :visible.sync="editVisible" width="30%">
            <el-form ref="form" :model="form" label-width="70px">
                <el-form-item label="商品名">
                    <el-input v-model="form.shopName"></el-input>
                </el-form-item>
                <el-form-item label="商品价格">
                    <el-input v-model="form.shopPrice"></el-input>
                </el-form-item>
                <el-form-item label="商品信息">
                    <el-input v-model="form.shopInfo"></el-input>
                </el-form-item>
                <el-form-item label="商品状态">
                    <el-select v-model="form.shopState" placeholder="请选择">
                        <el-option label="可售" :value="1">
                        </el-option>
                        <el-option label="售完" :value="0">
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="updateShop">确 定</el-button>
            </span>
        </el-dialog>

        <!-- 增加弹出框 -->
        <el-dialog title="增加信息" :visible.sync="addVisible" width="40%">
            <el-form ref="form" :model="form" label-width="70px">
                <el-form-item label="商品名">
                    <el-input v-model="shop.shopName"></el-input>
                </el-form-item>
                <el-form-item label="商品价格">
                    <el-input v-model="shop.shopPrice"></el-input>
                </el-form-item>
                <el-form-item label="商品信息">
                    <el-input v-model="shop.shopInfo"></el-input>
                </el-form-item>
                <el-form-item label="商品状态">
                    <el-select v-model="shop.shopState" placeholder="请选择">
                        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addVisible = false">取 消</el-button>
                <el-button type="primary" @click="addShop">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: 'basetable',
        data() {
            return {
                query: {
                    name: '',
                    pageIndex: 1,
                    pageSize: 5
                },
                shop: {},
                options: [{
                    value: '0',
                    label: '售完'
                },
                {
                    value: '1',
                    label: '可售'
                }],
                tableData: [],
                multipleSelection: [],
                delList: [],
                editVisible: false,
                addVisible: false,
                pageTotal: 0,
                form: {},
                idx: -1,
                id: -1
            };
        },
        mounted() {
            this.getData()
        },
        methods: {
            //获取分页数据
            getData() {
                let data = this.$qs.stringify({
                    pageSize: this.query.pageSize,
                    pageIndex: this.query.pageIndex
                })
                this.$axios.all([this.$axios.post('/shops/getAll', data), this.$axios.get('/shops/count')])
                    .then((res) => {
                        this.tableData = res[0].data
                        this.pageTotal = res[1].data
                    })
            },
            // 触发搜索按钮
            handleSearch() {
                let data = this.$qs.stringify({
                    shopName: this.query.name,
                    pageSize: this.query.pageSize,
                    pageIndex: this.query.pageIndex
                })
                this.$axios.all([this.$axios.post('/shops/selectByLike', data), this.$axios.post('/shops/likecount', data)])
                    .then((res) => {
                        this.tableData = res[0].data
                        this.pageTotal = res[1].data
                    })
            },
            // 删除操作
            handleDelete(index, row) {
                let data = this.$qs.stringify({
                    shopId: row.shopId
                })
                // 二次确认删除
                this.$confirm('确定要删除吗？', '提示', {
                    type: 'warning'
                })
                    .then(() => {
                        this.$axios.post('/shops/delete', data)
                        this.$message.success('删除成功')
                        this.tableData.splice(index, 1)
                        this.getData()
                    })
                    .catch(() => { });
            },
            // 多选操作
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            delAllSelection() {
                const length = this.multipleSelection.length;
                let str = '';
                this.delList = this.delList.concat(this.multipleSelection);
                for (let i = 0; i < length; i++) {
                    str += this.multipleSelection[i].shopName + ' ';
                }
                this.$message.success(`删除了${str}`);
                this.multipleSelection = [];
            },
            // 编辑操作
            handleEdit(index, row) {
                this.idx = index;
                this.form = row;
                this.editVisible = true;
            },

            //增加操作
            handleAdd() {
                this.addVisible = true;
            },
            // 保存编辑
            updateShop() {
                this.editVisible = false;
                this.$axios.post('/shops/update', this.form).then(res => {
                    this.$message.success(`修改第 ${this.idx + 1} 行成功`);
                    this.getData()
                })

            },
            //保存增加
            addShop() {
                this.$axios.post('/shops/add', this.shop).then(res => {
                    this.addVisible = false;
                    if (res.data.msg == '添加成功') {
                        this.$message.success('增加成功');
                    } else {
                        this.$message.success('您添加的商品名已存在');
                    }
                    this.getData()
                    this.shop = {}
                })
            },
            // 分页导航
            handlePageChange(val) {
                this.$set(this.query, 'pageIndex', val);
                this.getData();
            },

            //获取分页数据
            getPageData() {

            }
        }
    };
</script>

<style scoped>
    .handle-box {
        margin-bottom: 20px;
    }

    .handle-select {
        width: 120px;
    }

    .handle-input {
        margin-left: 10px;
        width: 280px;
        display: inline-block;
    }

    .table {
        width: 100%;
        font-size: 14px;
    }

    .red {
        color: #ff0000;
    }

    .mr10 {
        margin-right: 10px;
    }

    .table-td-thumb {
        display: block;
        margin: auto;
        width: 40px;
        height: 40px;
    }
</style>