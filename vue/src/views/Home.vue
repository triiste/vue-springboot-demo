<template>
    <div>
        <el-row>
            <el-col :span="12">
            <div id="main" style="width: 500px;height: 400px"></div>
        </el-col>
            <el-col :span="12">
                <div id="pie" style="width: 500px;height: 400px"></div>
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
                xAxis: {
                    type: 'category',
                    data: []
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        data: [],
                        type: 'line'
                    },
                    {
                        data: [],
                        type: 'bar'
                    }
                ]
            };
            var chartDom = document.getElementById('main');
            var myChart = echarts.init(chartDom);

            request.get("/echarts/mark",{
                params: {
                    userid: this.user.userid,
                }
            }).then(res => {
                //
                console.log("看看图"+res);
                for(var i=0;i<res.length;i++){
                    option.xAxis.data[i]=res[i].projectid;
                    option.series[0].data[i]=res[i].mark;
                    option.series[1].data[i]=res[i].mark;
                }
                myChart.setOption(option);
            });

            //饼图

            var pieoption = {
                title: {
                    text: 'Referer of a Website',
                    subtext: 'Fake Data',
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
                            { value: 1048, name: 'Search Engine' },
                            { value: 735, name: 'Direct' },
                            { value: 580, name: 'Email' },
                            { value: 484, name: 'Union Ads' },
                            { value: 300, name: 'Video Ads' }
                        ],
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };
            var pieDom = document.getElementById('pie');
            var pieChart = echarts.init(pieDom);
            pieChart.setOption(pieoption);


        }
    }
</script>
<style scoped>

</style>
