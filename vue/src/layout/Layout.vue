<template>
  <!--<img class="card" style="width: 100%" src="@/assets/demo1.jpg" >-->
  <div >

    <!--    头部-->
    <Header></Header>
    <!--    主体-->
    <div style="display:flex">
      <!--      侧边栏-->
      <Aside></Aside>
      <!--      内容区域-->
      <router-view style="flex: 1px"/>
    </div>
  </div>

</template>

<script>

import Header from "@/components/Header";
import Aside from "@/components/Aside";
import imgSrc from "@/assets/demo1.jpg";
export default {
    name: "Layout",
    data() {
        // return {
        //     // 顶部导航背景图片配置
        //     background: {
        //         // 背景图片地址
        //         backgroundImage: 'url(' + require('@/assets/demo1.jpg') + ')',
        //         // 背景图片是否重复
        //         backgroundRepeat: 'no-repeat',
        //         // 背景图片大小
        //         backgroundSize: 'cover',
        //         // 背景颜色
        //         backgroundColor: '#000',
        //         // 背景图片位置
        //         backgroundPosition: 'center top'
        //     },
        //
        // }
    },
    components: {
        Header,
        Aside
    },
}
</script>

<style  scoped>
  .bgBackground{
    width:100%;
    height:auto;
  }
</style>