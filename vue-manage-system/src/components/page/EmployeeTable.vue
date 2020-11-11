<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item> <i class="el-icon-lx-cascades"></i> 员工管理 </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-button type="primary" icon="el-icon-circle-plus-outline" @click="handleAdd">增加员工</el-button>
                <el-input v-model="query.employeeName" placeholder="姓名" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索员工</el-button>
            </div>
            <el-table
                :data="tableData"
                border
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
                @selection-change="handleSelectionChange"
            >
                <el-table-column type="selection" width="55" align="center"></el-table-column>
                <el-table-column prop="employeeName" label="姓名"></el-table-column>
                <el-table-column label="性别">
                    <template slot-scope="scope">
                        <el-tag>{{ scope.row.employeeSex == 0 ? '女' : '男' }}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="employeeAge" label="年龄"></el-table-column>
                <el-table-column prop="employeePhone" label="手机号"></el-table-column>
                <el-table-column label="角色">
                    <template slot-scope="scope">
                        <el-tag v-if="scope.row.employeeRole == 0">普通员工</el-tag>
                        <el-tag v-else-if="scope.row.employeeRole == 1">经理</el-tag>
                        <el-tag v-else>老板</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="状态">
                    <template slot-scope="scope">
                        <el-tag v-if="scope.row.employeeState == 0">休息</el-tag>
                        <el-tag v-else>上班</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">编辑 </el-button>
                        <el-button type="text" icon="el-icon-delete" class="red" @click="handleDelete(scope.$index, scope.row)"
                            >删除</el-button
                        >
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination
                    background
                    layout="total, prev, pager, next"
                    :current-page="query.pageIndex"
                    :page-size="query.pageSize"
                    :total="pageTotal"
                    @current-change="handlePageChange"
                ></el-pagination>
            </div>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" :visible.sync="editVisible" width="30%">
            <el-form ref="form" :model="form" label-width="70px">
                <el-form-item label="姓名">
                    <el-input v-model="form.employeeName"></el-input>
                </el-form-item>
                <el-form-item label="性别">
                    <el-select v-model="form.employeeSex" placeholder="请选择">
                        <el-option label="女" value="0"> </el-option>
                        <el-option label="男" value="1"> </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="年龄">
                    <el-input v-model="form.employeeAge"></el-input>
                </el-form-item>
                <el-form-item label="手机号">
                    <el-input v-model="form.employeePhone"></el-input>
                </el-form-item>
                <el-form-item label="角色">
                    <el-select v-model="form.employeeRole" placeholder="请选择">
                        <el-option label="普通员工" :value="0"> </el-option>
                        <el-option label="经理" :value="1"> </el-option>
                        <el-option label="老板" :value="2"> </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="状态">
                    <el-select v-model="form.employeeState" placeholder="请选择">
                        <el-option label="休息" :value="0"> </el-option>
                        <el-option label="上班中" :value="1"> </el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确 定</el-button>
            </span>
        </el-dialog>

        <!-- 增加弹出框 -->
        <el-dialog title="增加" :visible.sync="addVisible" width="40%">
            <el-form ref="form" :model="form" label-width="70px">
                <el-form-item label="姓名">
                    <el-input v-model="employee.employeeName"></el-input>
                </el-form-item>
                <el-form-item label="性别">
                    <el-select v-model="employee.employeeSex" placeholder="请选择">
                        <el-option label="女" :value="0"> </el-option>
                        <el-option label="男" :value="1"> </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="年龄">
                    <el-input v-model="employee.employeeAge"></el-input>
                </el-form-item>
                <el-form-item label="手机号">
                    <el-input v-model="employee.employeePhone"></el-input>
                </el-form-item>
                <el-form-item label="角色">
                    <el-select v-model="employee.employeeRole" placeholder="请选择">
                        <el-option label="普通员工" :value="0"> </el-option>
                        <el-option label="经理" :value="1"> </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="状态">
                    <el-select v-model="employee.employeeState" placeholder="请选择">
                        <el-option
                        v-for="item in options"
                        :label="item.label"
                        :value="item.value"
                        :key="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addVisible = false">取 消</el-button>
                <el-button type="primary" @click="addEmployee">确 定</el-button>
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
                    employeeName: '',
                    pageIndex: 1,
                    pageSize: 5
                },
                options:[
                    {
                        label:'休息',
                        value: 0
                    },
                    {
                        label: '上班中',
                        value: 1
                    }
                ],
                employee:{},
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
            // 获取 easy-mock 的模拟数据
            getData() {
                let data = this.$qs.stringify({
                    pageSize: this.query.pageSize,
                    pageIndex: this.query.pageIndex
                });
                this.$axios.all([this.$axios.post('/employee/getAll', data), this.$axios.get('/employee/count')]).then((res) => {
                    this.tableData = res[0].data;
                    this.pageTotal = res[1].data;
                    
                });
            },
            // 触发搜索按钮
            handleSearch() {
                let data = this.$qs.stringify({
                    employeeName: this.query.employeeName,
                    pageSize: this.query.pageSize,
                    pageIndex: this.query.pageIndex
                })
                this.$axios.all([this.$axios.post('/employee/selectByLike', data), this.$axios.post('/employee/likecount', data)])
                    .then((res) => {
                        this.tableData = res[0].data
                        this.pageTotal = res[1].data
                        
                    })
            },
            // 删除操作
            handleDelete(index, row) {
                let data = this.$qs.stringify({
                    employeeId: row.employeeId
                })
                // 二次确认删除
                this.$confirm('确定要删除吗？', '提示', {
                    type: 'warning'
                })
                    .then(() => {
                        this.$axios.post('/employee/delete', data)
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
                this.$axios.post('/employee/update', this.form).then((res) => {
                    this.$message.success(`修改第 ${this.idx + 1} 行成功`);
                    this.getData();
                });
            },
            // 增加操作
            handleAdd() {
                this.addVisible = true;
            },
            // 保存增加
            addEmployee() {
                this.employee.employeePhone = parseInt(this.employee.employeePhone)
                this.$axios.post('/employee/add', this.employee).then(res => {
                    this.addVisible = false;
                    this.$message.success('添加成功');
                    this.getData()
                    this.shop = {}
                })
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