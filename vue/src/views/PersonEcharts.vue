<template>
    <div style="overflow: hidden">
        <el-row :gutter="10" style="margin-bottom: 60px" >
            <el-col :span="8">
                <el-card style="color: #409EFF">
                    <div><i class="el-icon-user-solid" /> 个人项目所得积分</div>
                    <div style="padding: 10px 0; text-align: center; font-weight: bold" >
                        {{ persontotal }}
                    </div>
                </el-card>
            </el-col>
            <el-col :span="8">
                <el-card style="color: #F56C6C">
                    <div><i class="el-icon-money" /> 室主任奖励积分</div>
                    <div style="padding: 10px 0; text-align: center; font-weight: bold">
                        {{ office }}
                    </div>
                </el-card>
            </el-col>
            <el-col :span="8">
                <el-card style="color: #67C23A">
                    <div><i class="el-icon-bank-card" /> 所领导奖励积分</div>
                    <div style="padding: 10px 0; text-align: center; font-weight: bold">
                        {{ place}}
                    </div>
                </el-card>
            </el-col>
            <!--<el-col :span="8">-->
            <!--<el-card style="color: #E6A23C">-->
            <!--<div><i class="el-icon-s-shop" /> 门店总数</div>-->
            <!--<div style="padding: 10px 0; text-align: center; font-weight: bold">-->
            <!--20-->
            <!--</div>-->
            <!--</el-card>-->
            <!--</el-col>-->
        </el-row>
        <el-row>
            <el-col :span="24">
                <div id="main1" style="width: 1000px;height: 400px;margin-top: 20px;margin-left: 200px">

                </div>
            </el-col>
            <!--<el-col :span="12">-->
            <!--<div id="pile" style="width: 500px;height: 400px;margin-top: 200px"></div>-->
            <!--</el-col>-->
        </el-row>

    </div>
</template>
<script>
    import * as echarts from 'echarts';
    import request from "@/utils/request";
    export default {
        name: 'Home',
        data(){
            return{
                num1:666,
                var:66,
                persontotal:0,
                office:0,
                place:0,
            }

        },
        created(){
            let userStr = localStorage.getItem("user") || "{}";
            this.user = JSON.parse(userStr);
            //this.role = JSON.parse(localStorage.getItem("user")).role;  //取缓存里面的值
            //请求服务端，确认当前登录用户的合法信息，服务器传不过来
            //下面这是一种交互方式，传来后台的form表单
            request.get("/user/" + this.user.userid).then(res => {
                if (res.code === '0') {
                    console.log("看看此时后端传来的室主任积分"+res.data.mark);
                    this.user = res.data;
                    this.office=this.user.mark;
                    this.place=this.user.placeMark;
                }
            });

            request.get("/echarts/" + this.user.userid).then(res => {
                    this.persontotal = res
            });

            //求解persontotal






        },
        //页面元素渲染之后调用
        mounted(){

            // var option;
            // option = {
            //     title: {
            //         text: '参与项目统计',
            //         subtext: '积分图',
            //         left: 'center'
            //     },
            //     xAxis: {
            //         type: 'category',
            //         data: []
            //     },
            //     yAxis: {
            //         type: 'value'
            //     },
            //     series: [
            //         {
            //             data: [],
            //             type: 'line'
            //         },
            //         {
            //             data: [],
            //             type: 'bar'
            //         }
            //     ]
            // };
            // var chartDom = document.getElementById('main');
            // var myChart = echarts.init(chartDom);
            // request.get("/echarts/mark",{
            //     params: {
            //         userid: this.user.userid,
            //     }
            // }).then(res => {
            //     //
            //     // console.log("看看图"+res);
            //     for(var i=0;i<res.length;i++){
            //         option.xAxis.data[i]=res[i].projectid;
            //         option.series[0].data[i]=res[i].mark;
            //         option.series[1].data[i]=res[i].mark;
            //     }
            //     myChart.setOption(option);
            // });


            var option1;
            option1 = {
                tooltip: {
                    trigger: 'item',

                },
                title: {
                    text: '项目种类统计',
                    subtext: '个人项目积分',
                    left: 'center'
                },
                xAxis: {
                    type: 'category',
                    data: ["计划科研项目","专项任务","临时性研究任务","服务支撑业务"]
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    // {
                    //     data: [15,20,26,23],
                    //     type: 'line'
                    // },
                    {
                        data: [],
                        type: 'bar',
                        label: {
                            // 柱图头部显示值
                            show: true,
                            position: "top",
                            color: "#333",
                            fontSize: "12px",
                            formatter: (params) => {
                                return params.value[params.encode.x[0]];
                            },
                        }
                    },
                ]
            };
            var chartDom1 = document.getElementById('main1');
            var myChart1 = echarts.init(chartDom1);
            request.get("/echarts/personmark",{
                params: {
                    id: this.user.userid,
                }
            }).then(res => {
                //
                // console.log("看看图"+res);
                for(var i=0;i<4;i++){
                    // option1.xAxis.data[i]=res[i].projectid;
                    option1.series[0].data[i]=res[i];
                    // option1.series[1].data[i]=res[i].mark;
                }

                myChart1.setOption(option1);
            });

        }
    }
</script>
<style scoped>

</style>
