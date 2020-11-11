<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item> <i class="el-icon-lx-cascades"></i> 订单管理 </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-input v-model="query.searchWord" placeholder="订单号 或 收货人姓名 或 收货人手机号" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索订单</el-button>
            </div>
            <el-table :data="tableData" border class="table" ref="multipleTable" header-cell-class-name="table-header"
                @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="55" align="center"></el-table-column>
                <el-table-column prop="orderNo" label="订单号"></el-table-column>
                <el-table-column prop="orderTime" label="下单时间"></el-table-column>
                <el-table-column prop="shopName" label="商品名称"></el-table-column>
                <el-table-column prop="customerName" label="顾客名字"></el-table-column>
                <el-table-column prop="customerPhone" label="顾客电话"></el-table-column>
                <el-table-column prop="customerAddress" label="配送地址"></el-table-column>
                <el-table-column label="订单状态">
                    <template slot-scope="scope">
                        <el-tag v-if="scope.row.orderState == 0">接单中</el-tag>
                        <el-tag v-else-if="scope.row.orderState == 1">已接单</el-tag>
                        <el-tag v-else-if="scope.row.orderState == 2">配送中</el-tag>
                        <el-tag v-else>已完成</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button type="text" v-if="scope.row.orderState == 0 || scope.row.orderState == 1 ||scope.row.orderState == 2"
                            icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                        <el-button type="text" v-if="scope.row.orderState == 0 || scope.row.orderState == 1 ||scope.row.orderState == 2" 
                            icon="el-icon-delete" class="red" @click="handleCancel(scope.$index, scope.row)">取消订单
                        </el-button>
                        <el-button type="text" v-if="scope.row.orderState == 3" icon="el-icon-delete" class="red"
                            @click="handleDelete(scope.$index, scope.row)">删除记录</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination background layout="total, prev, pager, next" :current-page="query.pageIndex"
                    :page-size="query.pageSize" :total="pageTotal" @current-change="handlePageChange"></el-pagination>
            </div>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" :visible.sync="editVisible" width="40%">
            <el-form ref="form" :model="form" label-width="90px">
                <el-form-item label="订单号">
                    <el-input v-model="form.orderNo" readonly></el-input>
                </el-form-item>
                <el-form-item label="下单时间">
                    <el-input v-model="form.orderTime" readonly></el-input>
                </el-form-item>
                <el-form-item label="商品名称">
                    <el-input v-model="form.shopName" readonly></el-input>
                </el-form-item>
                <el-form-item label="收货人姓名">
                    <el-input v-model="form.customerName"></el-input>
                </el-form-item>
                <el-form-item label="收货人电话">
                    <el-input v-model="form.customerPhone"></el-input>
                </el-form-item>
                <el-form-item label="收货地址">
                    <el-input v-model="form.customerAddress"></el-input>
                </el-form-item>
                <el-form-item label="订单状态">
                    <el-select v-model="form.orderState" placeholder="请选择">
                        <el-option label="接单中" :value="0">
                        </el-option>
                        <el-option label="已接单" :value="1">
                        </el-option>
                        <el-option label="配送中" :value="2">
                        </el-option>
                        <el-option label="已完成" :value="3">
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确 定</el-button>
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
                    searchWord: '',
                    pageIndex: 1,
                    pageSize: 5
                },
                tableData: [],
                multipleSelection: [],
                delList: [],
                editVisible: false,
                pageTotal: 0,
                form: {},
                idx: -1,
                id: -1
            };
        },
        mounted() {
            this.getData();
        },
        methods: {
            //
            getData() {
                let data = this.$qs.stringify({
                    pageSize: this.query.pageSize,
                    pageIndex: this.query.pageIndex
                });
                this.$axios.all([this.$axios.post('/order/getAll', data), this.$axios.get('/order/count')]).then((res) => {
                    this.tableData = res[0].data;
                    this.pageTotal = res[1].data;
                });
            },
            // 触发搜索按钮
            handleSearch() {
                let data = this.$qs.stringify({
                    searchWord: this.query.searchWord,
                    pageSize: this.query.pageSize,
                    pageIndex: this.query.pageIndex
                })
                this.$axios.all([this.$axios.post('/order/selectByLike', data), this.$axios.post('/order/likecount', data)])
                    .then((res) => {
                        this.tableData = res[0].data
                        this.pageTotal = res[1].data
                        console.log(res[1].data);
                    })
            },
            // 取消订单操作
            handleCancel(index, row) {
                let data = this.$qs.stringify({
                    orderId:row.orderId
                })
                // 二次确认
                this.$confirm('确定要取消订单吗？', '提示', {
                    type: 'warning'
                })
                    .then(() => {
                        this.$axios.post('/order/delete',data)
                        this.$message.success('取消成功');
                        this.tableData.splice(index, 1);
                        this.getData()
                    })
                    .catch(() => { });
            },
            // 删除操作
            handleDelete(index, row) {
                let data = this.$qs.stringify({
                    orderId:row.orderId
                })
                // 二次确认删除
                this.$confirm('确定要删除记录吗？', '提示', {
                    type: 'warning'
                })
                    .then(() => {
                        this.$axios.post('/order/delete',data)
                        this.$message.success('取消成功');
                        this.tableData.splice(index, 1);
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
                    str += this.multipleSelection[i].name + ' ';
                }
                this.$message.error(`删除了${str}`);
                this.multipleSelection = [];
            },
            // 编辑操作
            handleEdit(index, row) {
                this.idx = index;
                this.form = row;
                this.editVisible = true;
            },
            // 保存编辑
            saveEdit() {
                this.editVisible = false;
                this.$axios.post('/order/update', this.form).then((res) => {
                    this.$message.success(`修改第 ${this.idx + 1} 行成功`);
                    this.getData();
                });
            },
            // 分页导航
            handlePageChange(val) {
                this.$set(this.query, 'pageIndex', val);
                this.getData();
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
        width: 300px;
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