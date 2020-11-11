<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="10">
                <el-card shadow="hover" class="mgb20" style="height:252px;">
                    <div class="user-info">
                        <img src="../../assets/img/img.jpg" class="user-avator" alt />
                        <div class="card">
                            <p>用户名：<span>{{$store.state.user.employee.employeeName}}</span></p>
                            <p>职位：<span>{{role}}</span></p>
                        </div>
                    </div>
                    <!-- <div class="user-info-list">
                        上次登录时间：
                        <span>2019-11-01</span>
                    </div>
                    <div class="user-info-list">
                        上次登录地点：
                        <span>东莞</span>
                    </div> -->
                </el-card>
            </el-col>
            <el-col :span="12" class="top">
                <el-row :gutter="20" class="mgb20">
                    <el-col :span="11">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-1">
                                <i class="el-icon-s-order grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">{{baseData.dayOrderCount}}</div>
                                    <div>今日完成订单量</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="11">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-2">
                                <i class="el-icon-goods grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">{{baseData.dayOrderProfit ? baseData.dayOrderProfit : 0}}
                                    </div>
                                    <div>今日总盈利</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="11">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-3">
                                <i class="el-icon-s-order grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">{{baseData.weekOrderCount}}</div>
                                    <div>近一周完成订单量</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="11">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-3">
                                <i class="el-icon-goods grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">{{baseData.weekOrderProfit ? baseData.weekOrderProfit : 0}}
                                    </div>
                                    <div>近一周总盈利</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                </el-row>
            </el-col>
        </el-row>
        <el-row :gutter="10">
            <el-col :span="11">
                <el-card shadow="hover">
                    <div class="echart" ref="bar"></div>
                </el-card>
            </el-col>
            <el-col :span="11">
                <el-card shadow="hover">
                    <div class="echart" ref="line"></div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import bus from '../common/bus';
    export default {
        name: 'dashboard',
        data() {
            return {
                baseData: {},
                options: [
                    {
                        title: {
                            text: '近一周菜品订单量'
                        },
                        color: ['#3398DB'],
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                            }
                        },
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            containLabel: true
                        },
                        xAxis: [
                            {
                                type: 'category',
                                data: ['青椒肉丝', '干煸土豆丝', '肉末茄子', '水煮肉片', '鱼香肉丝', '红烧排骨'],
                                axisTick: {
                                    alignWithLabel: true
                                }
                            }
                        ],
                        yAxis: [
                            {
                                type: 'value'
                            }
                        ],
                        series: [
                            {
                                name: '订单数量',
                                type: 'bar',
                                barWidth: '50%',
                                data: []
                            }
                        ]
                    }, {
                        title: {
                            text: '近一周订单量，盈利'
                        },
                        tooltip: {
                            trigger: 'axis'
                        },
                        legend: {
                            data: ['订单量', '盈利']
                        },
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            containLabel: true
                        },
                        xAxis: {
                            type: 'category',
                            boundaryGap: false,
                            data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [
                            {
                                name: '订单量',
                                type: 'line',
                                stack: '总量',
                                data: [120, 132, 101, 134, 90, 230, 210]
                            },
                            {
                                name: '盈利',
                                type: 'line',
                                stack: '总量',
                                data: [220, 182, 191, 234, 290, 330, 310]
                            }
                        ]
                    }]

            };
        },
        computed: {
            role() {
                let role = this.$store.state.user.employee.employeeRole
                if (role == 0) {
                    return '普通员工'
                } else if (role == 1) {
                    return '经理'
                } else {
                    return '老板'
                }
            }
        },
        created() {
            //在页面刷新时将vuex里的信息保存到localStorage里
            window.addEventListener("beforeunload", () => {
                localStorage.setItem("messageStore", JSON.stringify(this.$store.state))
            })
            //在页面加载时读取localStorage里的状态信息
            localStorage.getItem("messageStore") && this.$store.replaceState(Object.assign(this.$store.state, JSON.parse(localStorage.getItem("messageStore"))));
        },
        mounted() {
            this.getData(),
                this.initCharts()
        },
        methods: {
            getData() {
                this.$axios.get('/basedata/getdata').then(res => {
                    this.baseData = res.data
                })

            },
            initCharts() {
                let myChart1 = this.$echarts.init(this.$refs.bar);
                let myChart2 = this.$echarts.init(this.$refs.line)
                // // 绘制图表
                this.$axios.get('/basedata/foodorder').then(res => {
                    this.options[0].series[0].data = res.data
                    myChart1.setOption(this.options[0]);
                })
                this.$axios.get('/basedata/linedata').then(res => {
                    this.options[1].series[0].data = res.data.orderList
                    this.options[1].series[1].data = res.data.profitList
                    myChart2.setOption(this.options[1])
                })

            }
        }
    };
</script>


<style scoped>
    .el-row {
        margin-bottom: 20px;
    }

    .mgb20 {
        margin-bottom: 20px;
    }

    [class*=el-col-] {
        margin-top: 20px;
        margin-left: 20px;
    }

    .grid-content {
        display: flex;
        align-items: center;
        height: 100px;
    }

    .grid-cont-right {
        flex: 1;
        text-align: center;
        font-size: 14px;
        color: #999;
    }

    .grid-num {
        font-size: 30px;
        font-weight: bold;
    }

    .grid-con-icon {
        font-size: 50px;
        width: 80px;
        height: 100px;
        text-align: center;
        line-height: 100px;
        color: #fff;
    }

    .grid-con-1 .grid-con-icon {
        background: rgb(45, 140, 240);
    }

    .grid-con-1 .grid-num {
        color: rgb(45, 140, 240);
    }

    .grid-con-2 .grid-con-icon {
        background: rgb(100, 213, 114);
    }

    .grid-con-2 .grid-num {
        color: rgb(45, 140, 240);
    }

    .grid-con-3 .grid-con-icon {
        background: rgb(242, 94, 67);
    }

    .grid-con-3 .grid-num {
        color: rgb(242, 94, 67);
    }

    .user-info {
        position: relative;
        display: flex;
        align-items: center;
        padding-bottom: 20px;
        border-bottom: 2px solid #ccc;
        margin-bottom: 20px;
    }

    .user-avator {
        width: 120px;
        height: 120px;
        border-radius: 50%;
    }

    .card {
        position: absolute;
        top: 30px;
        left: 180px;
    }

    .card p {
        margin-bottom: 10px;
        font-size: 18px;
        font-weight: 600;
    }

    .mgb20 {
        margin-bottom: 20px;
    }

    .echart {
        width: 100%;
        height: 300px;
    }
</style>