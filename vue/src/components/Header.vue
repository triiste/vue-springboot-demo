<template>
    <div style="height: 50px; line-height: 50px;border-bottom: 1px solid #ccc;display: flex" >
        <div style="width: 200px; padding-left: 30px; font-weight: bold; color: #409EFF;">后台管理</div>
        <div style="flex: 1px;"></div>
        <div style="width: 100px">
          <el-dropdown>
            <span class="el-dropdown-link">
              <br>{{username}}
              <el-icon class="el-icon--right">
                <arrow-down />
              </el-icon>
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item @click="$router.push('/person')">个人信息</el-dropdown-item>
                <el-dropdown-item @click="$router.push('/login')">退出系统</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
    </div>
</template>

<script>
    export default {
      name: "Header",
      props: ['user'],
      data() {
        return {
          username: ''
        }
      },
      created() {
       this.username = JSON.parse(localStorage.getItem("user")).username
        console.log(JSON.parse(localStorage.getItem("user")))
      },
      methods:{
      },
    }
</script>

<style scoped>

</style>