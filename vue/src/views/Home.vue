<template>
    <div>
        <el-row :gutter="10" style="margin-bottom: 60px">
            <el-col :span="8">
                <el-card style="color: #409EFF">

                    <div><i class="el-icon-user-solid" /> 全所项目总积分</div>
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
            <el-col :span="13">
            <div id="main" style="width: 800px;height: 400px;margin-top: 20px"></div>
        </el-col>
            <el-col :span="11">
                <div id="pie" style="width: 800px;height: 400px;margin-top: 20px"></div>
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
        },
        //页面元素渲染之后调用
        mounted(){

            var option;
            option = {
                title: {
                    text: '各室积分汇总',
                    subtext: '室内成员积分总和（包括成员所奖励积分和室奖励积分）',
                    left: 'center'
                },
                xAxis: {
                    type: 'category',
                    data: ["1室","2室","3室","4室","5室"]
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
                        data: [15,20,26,23,32],
                        type: 'bar'
                    }
                ]
            };
            var chartDom = document.getElementById('main');
            var myChart = echarts.init(chartDom);

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

            // });
              myChart.setOption(option);

            //饼图
            var pieoption = {
                title: {
                    text: '四类项目积分占比',
                    // subtext: '',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item'
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
                            { value: 652, name: '计划科研项目' },
                            { value: 735, name: '专项任务' },
                            { value: 580, name: '临时性研究任务'},
                            { value: 484, name: '支撑机关、服务部队任务' },
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
                console.log("看看res的类型"+res[0]);
                pieoption.series[0].data[0].value=res[0];
                pieoption.series[0].data[1].value=res[1];
                pieoption.series[0].data[2].value=res[2];
                pieoption.series[0].data[3].value=res[3];
                pieChart.setOption(pieoption);
            });
            // pieChart2.setOption(pieoption);
        }
    }
</script>
<style scoped>

</style>
