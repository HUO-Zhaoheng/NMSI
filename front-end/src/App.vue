<template>
<transition name="el-zoom-in-top">
  <div id="signin">
    <el-card class="signin_card">
      <mu-avatar src="src/assets/logo.jpeg" :size="300"/><br/>
      <div style="font-size:30px;font-weight:bold;">NMSI</div>
      <mu-text-field v-model="username" label="账号" hintText="用户名/邮箱/手机"/><br/>
      <mu-text-field v-model="password" label="密码" hintText="请输入密码" type="password" labelFloat/><br/>
      <!--<router-link to="/index">-->
        <mu-raised-button @click="signIn" label="登陆" class="md-button signin_raised-button" primary/><br>
      <!--</router-link><br/>-->
      <mu-flat-button label="forget password?" class="md-button" primary hoverColor="white"/>
    </el-card>
  </div>
</transition>
</template>

<script>
import router from 'vue-router'
export default {
  name: 'app',
  data () {
    return {
      password:"",
      username:""
    }
  },
  created: function() {
    if (!localStorage.booking)
      localStorage.booking = []
  },
  methods: {
    signIn() {
      if (this.username == "admin" && this.password == "123")
        this.$router.push("/index")
      else this.$message({
        message:"用户名或密码不正确",
        type:"error"
      })
    }
  }
}
</script>

<style lang="scss">
    html, body{
      width:100%;
      height:100%;
    }
    #app{
      width:100%;
      height:100%;
      background-color:#4159AA;
    }
    .md-button{
      border-radius: 2em !important;
      margin:10px;
    }
    #signin {
      width:100%;
      height:100%;
      font-family: 'Avenir', Helvetica, Arial, sans-serif;
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;
      text-align: center;
      color: #2c3e50;
      background-color: #4159AA;
      display: flex;
      flex-direction: row;
      justify-content: center;
      align-items: center;
      img{
        border-radius:0;
      }
      .el-card{
        border-radius: 1em !important;
      }
      .signin_card{
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        .signin_raised-button{
          margin:10px;
          width:40%;
        }
      }
    }
</style>
