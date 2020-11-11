<template>
    <div class="">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-copy"></i>消息通知</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
                <!-- <el-button type="primary" class="add" icon="el-icon-circle-plus-outline" @click="handleAdd">增加消息通知
                </el-button> -->
            <el-tabs v-model="message">
                <el-tab-pane :label="`未读消息(${unread.length})`" name="first">
                    <el-table :data="unread" :show-header="false" style="width: 100%">
                        <el-table-column>
                            <template slot-scope="scope">
                                <span class="message-title">{{scope.row.title}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column prop="date" width="180"></el-table-column>
                        <el-table-column width="120">
                            <template slot-scope="scope">
                                <el-button size="small" @click="handleRead(scope.$index,scope.row.noticeId)">标为已读
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <div class="handle-row">
                        <el-button type="primary" @click="handleReadAll">全部标为已读</el-button>
                    </div>
                </el-tab-pane>
                <el-tab-pane :label="`已读消息(${read.length})`" name="second">
                    <template v-if="message === 'second'">
                        <el-table :data="read" :show-header="false" style="width: 100%">
                            <el-table-column>
                                <template slot-scope="scope">
                                    <span class="message-title">{{scope.row.title}}</span>
                                </template>
                            </el-table-column>
                            <el-table-column prop="date" width="150"></el-table-column>
                            <el-table-column width="120">
                                <template slot-scope="scope">
                                    <el-button type="danger" @click="handleDel(scope.$index,scope.row.noticeId)">删除
                                    </el-button>
                                </template>
                            </el-table-column>
                        </el-table>
                        <div class="handle-row">
                            <el-button type="danger" @click="handleDelAll">删除全部</el-button>
                        </div>
                    </template>
                </el-tab-pane>
                <el-tab-pane :label="`回收站(${recycle.length})`" name="third">
                    <template v-if="message === 'third'">
                        <el-table :data="recycle" :show-header="false" style="width: 100%">
                            <el-table-column>
                                <template slot-scope="scope">
                                    <span class="message-title">{{scope.row.title}}</span>
                                </template>
                            </el-table-column>
                            <el-table-column prop="date" width="150"></el-table-column>
                            <el-table-column width="120">
                                <template slot-scope="scope">
                                    <el-button @click="handleRestore(scope.$index,scope.row.noticeId)">还原</el-button>
                                </template>
                            </el-table-column>
                        </el-table>
                        <div class="handle-row">
                            <el-button type="danger" @click="handelRemoveAll">清空回收站</el-button>
                        </div>
                    </template>
                </el-tab-pane>
            </el-tabs>
        </div>
        <!-- 增加弹出框 -->
        <!-- <el-dialog title="增加信息" :visible.sync="addVisible" width="30%">
            <el-form ref="form" :model="notice" label-width="70px">
                <el-form-item label="消息内容">
                    <el-input type="textarea" :autosize="{ minRows: 3, maxRows: 4}" placeholder="请输入内容"
                        v-model="notice.title">
                    </el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addVisible = false">取 消</el-button>
                <el-button type="primary" @click="addNotice">发布</el-button>
            </span>
        </el-dialog> -->
    </div>
</template>

<script>
    export default {
        name: 'tabs',
        data() {
            return {
                message: 'first',
                showHeader: false,
                unread: [],
                read: [],
                recycle: [],
                addVisible: false,
                notice: {
                    date: new Date(),
                    noticeState: 0
                }
            }
        },
        mounted() {
            this.getData()
        },
        methods: {
            getData() {
                this.unread = [],
                    this.read = [],
                    this.recycle = [],
                    this.$axios.get('/notice/getAll').then(res => {
                        res.data.forEach(item => {
                            if (item.noticeState == 0) {
                                this.unread.push(item)
                            } else if (item.noticeState == 1) {
                                this.read.push(item)
                            } else {
                                this.recycle.push(item)
                            }
                        })
                    })
            },
            handleRead(index, noticeId) {
                this.unread[index].noticeState = 1
                let noticeState = this.unread[index].noticeState
                let data = this.$qs.stringify({
                    noticeId,
                    noticeState
                });
                this.$axios.post('/notice/update', data).then(res => {
                    this.getData()
                })
            },
            handleDel(index, noticeId) {
                this.read[index].noticeState = 2
                let noticeState = this.read[index].noticeState
                let data = this.$qs.stringify({
                    noticeId,
                    noticeState
                });
                this.$axios.post('/notice/update', data).then(res => {
                    this.getData()
                })
            },
            handleRestore(index, noticeId) {
                this.recycle[index].noticeState = 1
                let noticeState = this.recycle[index].noticeState
                let data = this.$qs.stringify({
                    noticeId,
                    noticeState
                });
                this.$axios.post('/notice/update', data).then(res => {
                    this.getData()
                })
            },
            handleReadAll() {
                this.unread.forEach(item => {
                    item.noticeState = 1
                })
                this.$axios.post('/notice/updateAll', this.unread).then(res => {
                    this.getData()
                })
            },
            handleDelAll() {
                this.read.forEach(item => {
                    item.noticeState = 2
                })
                this.$axios.post('/notice/updateAll', this.read).then(res => {
                    this.getData()
                })
            },
            handelRemoveAll() {
                this.$axios.post('/notice/deleteAll', this.recycle).then(res => {
                    this.getData()
                })
            },
            // handleAdd() {
            //     this.addVisible = true;
            // },
            // addNotice() {
            //     this.addVisible = false;
            //     console.log(this.notice);
                                
            //     this.$axios.post('/notice/add', this.notice).then(res => {
            //         this.getData()
            //     })
            // }
        },
        computed: {
            unreadNum() {
                return this.unread.length;
            }
        }
    }

</script>

<style>
    .message-title {
        cursor: pointer;
    }

    .handle-row {
        margin-top: 30px;
    }

    .add {
        margin-bottom: 10px;
    }
</style>