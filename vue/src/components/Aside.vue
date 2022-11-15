<template>

  <el-menu
      style="width: 250px;min-height: calc(100vh - 50px)"
      default-active="user"
      router
      class="el-menu-vertical-demo">
    <el-sub-menu index="1" >
      <template #title>
        <span>后台管理</span>
      </template>
        <!--v-if="this.user.role != 1 && this.user.role != 2"-->
        <!--v-if="this.user.role != 1"-->
        <!--v-if="this.user.role != 1"-->

      <el-menu-item index="/project" v-if="this.user.role === 4 || this.user.role === 2"  >
        <span>项目总数</span>
        <el-icon><Document /></el-icon>
      </el-menu-item>
        <el-sub-menu index="/project" v-if="this.user.role === 2 || this.user.role === 4">
            <template #title>项目分类</template>
            <el-menu-item index="/project1" >计划科研项目 </el-menu-item>
            <el-menu-item index="/project2">专项任务</el-menu-item>
            <el-menu-item index="/project3">临时性研究任务</el-menu-item>
            <el-menu-item index="/project4">支撑机关，服务部队任务</el-menu-item>
        </el-sub-menu>



        <el-menu-item index="/officeperson" v-if="this.user.role !== 1"  >
          <span>用户管理</span>
          <el-icon><User /></el-icon>
        </el-menu-item>
        <!--<el-menu-item index="/group" >项目组管理</el-menu-item>-->
        <el-sub-menu index="/group" v-if="this.user.role === 1 || this.user.role === 3">
          <template #title>项目管理</template>
          <el-menu-item index="/joinproject" >参与项目</el-menu-item>
          <el-menu-item index="/hostproject">主持项目</el-menu-item>
        </el-sub-menu>
        <!--<el-menu-item index="/manager"  v-if="this.user.role === 3 || this.user.role === 5">室主任管理</el-menu-item>-->
        <!--<el-menu-item index="/science"  v-if="this.user.role === 4 || this.user.role === 5">科研处管理</el-menu-item>-->
        <el-menu-item index="/home">
            <span>图表数据统计(开发中)</span>
            <el-icon><PictureFilled /></el-icon>
        </el-menu-item>
        <el-menu-item index="/personEcharts">
            <span>图表数据统计(个人)</span>
            <el-icon><PictureFilled /></el-icon>
        </el-menu-item>
    </el-sub-menu>

<!--    <el-menu-item index="2">-->
<!--      <el-icon><icon-menu /></el-icon>-->
<!--      <span>其他管理</span>-->
<!--    </el-menu-item>-->
  </el-menu>
</template>

<script>
    import request from "@/utils/request";
    import {Location, Setting, Menu ,Document,User,PictureFilled} from '@element-plus/icons'

export default {

  name: "Aside",
    components: {
        Location, Setting, Menu ,Document,User,PictureFilled
    },
    data(){
      return{
          user:{},
          path:this.$route.path,
          role:''
      }
    },
    created(){
        //获得登录过程缓存的字符串
        let userStr = localStorage.getItem("user") || "{}"
        this.user =JSON.parse(userStr)

        this.role = JSON.parse(localStorage.getItem("user")).role;  //取缓存里面的值
      //请求服务端，确认当前登录用户的合法信息，服务器传不过来
        //下面这是一种交互方式，传来后台的form表单
        request.get("/user/" + this.user.userid).then(res => {
            if(res.code === '0'){
                this.user = res.data
            }
        })
    }
}


</script>

<style scoped>
    .el-menu-vertical-demo:not(.el-menu--collapse) {
        width: 200px;
        min-height: 400px;
    }
</style>