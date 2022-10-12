<template>
  <el-menu
      style="width: 200px;min-height: calc(100vh - 50px)"
      default-active="user"
      router
      class="el-menu-vertical-demo">
<!--      @open="handleOpen"-->
<!--      @close="handleClo  se"-->
    <el-sub-menu index="1" >
      <template #title>
        <span>后台管理</span>
      </template>
        <!--v-if="this.user.role != 1 && this.user.role != 2"-->
        <!--v-if="this.user.role != 1"-->
        <!--v-if="this.user.role != 1"-->
        <el-menu-item index="/user"  v-if="this.user.role != 1 && this.user.role != 2" >用户管理</el-menu-item>
        <el-menu-item index="/project" >项目管理</el-menu-item>
        <el-menu-item index="/group" >项目组管理</el-menu-item>
        <el-menu-item index="/manager"  v-if="this.user.role === 3 || this.user.role === 5">室主任管理</el-menu-item>
        <el-menu-item index="/science"  v-if="this.user.role === 4 || this.user.role === 5">科研处管理</el-menu-item>
    </el-sub-menu>
<!--    <el-menu-item index="2">-->
<!--      <el-icon><icon-menu /></el-icon>-->
<!--      <span>其他管理</span>-->
<!--    </el-menu-item>-->
  </el-menu>
</template>

<script>
    import request from "@/utils/request";
export default {
  name: "Aside",
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

</style>