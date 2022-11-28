<template>
    <div style="overflow: hidden">
        <el-row :gutter="10" style="margin-bottom: 60px" >
            <el-col :span="8">
                <el-card style="color: #409EFF">

                    <div><i class="el-icon-user-solid" /> 全所项目积分和</div>
                    <div style="padding: 10px 0; text-align: center; font-weight: bold" >
                        {{ persontotal }}
                    </div>

                </el-card>
            </el-col>

            <el-col :span="8">
                <el-card style="color: #F56C6C">
                    <div><i class="el-icon-money" /> 全所室主任奖励积分总和</div>
                    <div style="padding: 10px 0; text-align: center; font-weight: bold">
                        {{ office }}
                    </div>
                </el-card>
            </el-col>
            <el-col :span="8">
                <el-card style="color: #67C23A">
                    <div><i class="el-icon-bank-card" /> 所领导奖励积分总和</div>
                    <div style="padding: 10px 0; text-align: center; font-weight: bold">
                        {{ place}}
                    </div>
                </el-card>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="12">
            <div id="main" style="width: 800px;height: 400px;margin-top: 20px;overflow: hidden" ></div>
        </el-col>
            <el-col :span="12">
                <div id="pie" style="width: 800px;height: 400px;margin-top: 20px; overflow: hidden"></div>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="12">
                <div id="main1" style="width: 800px;height: 400px;margin-top: 20px;overflow: hidden" ></div>
            </el-col>
            <el-col :span="12">
                <div id="main2" style="width: 800px;height: 400px;margin-top: 20px;overflow: hidden" ></div>
            </el-col>
            <!--<el-col :span="12">-->
            <!--<div id="pie" style="width: 800px;height: 400px;margin-top: 20px; overflow: hidden"></div>-->
            <!--</el-col>-->
        </el-row>
        <el-row>
            <el-col :span="12">
                <div id="main3" style="width: 800px;height: 400px;margin-top: 20px;overflow: hidden" ></div>
            </el-col>
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
                    this.user = res.data
                }
            });
            request.get("/echarts/projectkind",{
            }).then(res => {
                for(var i=0;i<4;i++){
                    this.persontotal+=res[i];
                }
            });
            request.get("/echarts/officemark",{
            }).then(res => {
                this.office=res[0];
                this.place=res[1];

            });
        },
        //页面元素渲染之后调用
        mounted(){

            var option;
            option = {
                tooltip: {
                    trigger: 'item',

                },
                title: {
                    text: '各室成员项目积分汇总',
                    subtext: '室内成员仅项目积分总和',
                    left: 'center'
                },
                xAxis: {
                    type: 'category',
                    data: []
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
                            // formatter: (params) => {
                            //     return params.value[params.encode.x[0]];
                            // },
                        }
                    }
                ]
            };
            var chartDom = document.getElementById('main');
            var myChart = echarts.init(chartDom);
                //这是后端传来的各室内积分
            request.get("/echarts/officemarktotal",{
            }).then(res => {
                for(var i=0;i<res[0].length;i++){
                    option.series[0].data[i]=res[1][i];
                    option.xAxis.data[i]=res[0][i]+1+"室";
                }
                myChart.setOption(option);
            });



            //饼图
            var pieoption = {
                title: {
                    text: '四类项目积分占比',
                    // subtext: '',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item',

                },
                legend: {
                    orient: 'vertical',
                    left: 'left'
                },
                series: [
                    {
                        name: 'Access From',
                        type: 'pie',
                        radius: '50%',
                        data: [
                            { value: 0, name: '计划科研项目' },
                            { value: 0, name: '专项任务' },
                            { value: 0, name: '临时性研究任务'},
                            { value: 0, name: '服务支撑业务'},
                            // { value: 300, name: 'Video Ads' }
                        ],
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    },
                ]
            };


            var pieDom = document.getElementById('pie');
              // var pie2 = document.getElementById('pile');
                var pieChart = echarts.init(pieDom);
                // var pieChart2 = echarts.init(pie2);
            request.get("/echarts/projectkind",{
            }).then(res => {
                // console.log("看看res的类型"+res[0]);
                pieoption.series[0].data[0].value=res[0];
                pieoption.series[0].data[1].value=res[1];
                pieoption.series[0].data[2].value=res[2];
                pieoption.series[0].data[3].value=res[3];
                pieChart.setOption(pieoption);
            });


            //各室主持项目汇总
            var option1;
            option1 = {
                tooltip: {
                    trigger: 'item',

                },
                title: {
                    text: '各室主持项目汇总',
                    // subtext: '室内成员仅项目积分总和',
                    left: 'center'
                },
                xAxis: {
                    type: 'category',
                    data: []
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
                            // formatter: (params) => {
                            //     return params.value[params.encode.x[0]];
                            // },
                        }
                    }
                ]
            };
            var chartDom1 = document.getElementById('main1');
            var myChart1 = echarts.init(chartDom1);
            //这是后端传来的各室内积分
            request.get("/echarts/officetotal",{
            }).then(res => {
                console.log(res[0]);
                console.log(res[1]);
                for(var i=0;i<res[0].length;i++){
                    // console.log("这是数量"+res[1][i])
                    console.log("这是室内"+res[0][i])
                    option1.series[0].data[i]=res[1][i];
                    option1.xAxis.data[i]=res[0][i]+1+"室";
                    myChart1.setOption(option1);
                }

            });

            //各室室主任奖励积分汇总
            var option2;
            option2 = {
                tooltip: {
                    trigger: 'item',

                },
                title: {
                    text: '各室室主任奖励积分汇总',
                    // subtext: '室内成员仅项目积分总和',
                    left: 'center'
                },
                xAxis: {
                    type: 'category',
                    data: []
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
                            // formatter: (params) => {
                            //     return params.value[params.encode.x[0]];
                            // },
                        }
                    }
                ]
            };
            var chartDom2 = document.getElementById('main2');
            var myChart2 = echarts.init(chartDom2);
            //这是后端传来的各室内积分
            request.get("/echarts/officereward",{
            }).then(res => {
                console.log(res[0]);
                console.log(res[1]);
                for(var i=0;i<res[0].length;i++){
                    // console.log("这是数量"+res[1][i])
                    console.log("这是室内"+res[0][i])
                    option2.series[0].data[i]=res[1][i];
                    option2.xAxis.data[i]=res[0][i]+1+"室";
                    myChart2.setOption(option2);
                }

            });


            //各室所领导奖励积分汇总
            var option3;
            option3 = {
                tooltip: {
                    trigger: 'item',

                },
                title: {
                    text: '各室所领导奖励积分汇总',
                    // subtext: '室内成员仅项目积分总和',
                    left: 'center'
                },
                xAxis: {
                    type: 'category',
                    data: []
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
                            // formatter: (params) => {
                            //     return params.value[params.encode.x[0]];
                            // },
                        }
                    }
                ]
            };
            var chartDom3 = document.getElementById('main3');
            var myChart3 = echarts.init(chartDom3);
            //这是后端传来的各室内积分
            request.get("/echarts/palcereward",{
            }).then(res => {
                console.log(res[0]);
                console.log(res[1]);
                for(var i=0;i<res[0].length;i++){
                    // console.log("这是数量"+res[1][i])
                    console.log("这是室内"+res[0][i])
                    option3.series[0].data[i]=res[1][i];
                    option3.xAxis.data[i]=res[0][i]+1+"室";
                    myChart3.setOption(option3);
                }

            });


        }
    }
</script>
<style scoped>


</style>
