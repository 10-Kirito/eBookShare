<template>
  <div class="base">
    <!-- 注册登录界面 -->
   <div  class="loginAndRegist">
        <!--登录表单-->
        <div  class="loginArea">
            <transition
                name="animate__animated animate__bounce"
                enter-active-class="animate__fadeInUp"
                leave-active-class="animate__zoomOut"
                appear
            >
            <!-- 标语 -->
            <div v-show="isShow" class="title">
                LOGIN
            </div>
            </transition>
            <transition
                name="animate__animated animate__bounce"
                enter-active-class="animate__fadeInUp"
                leave-active-class="animate__zoomOut"
                appear
            >
            <!-- 密码框和用户名框 -->

            <div v-show="isShow" class="pwdArea" >
              <div style="flex: 1;justify-content: center;display: flex;align-items: center">
                <el-form :model="loginUser" :rules="rules" ref="ruleForm">
                  <el-form-item  prop="username">
                     <el-input class="username" v-model="loginUser.username" style="width: 165px"  placeholder="用户名"></el-input>
                  </el-form-item>
                  <el-form-item  prop="password">
                      <el-input placeholder="密码"  v-model="loginUser.password" style="width: 165px" show-password></el-input>
                  </el-form-item>
                </el-form>
              </div>
            </div>

            </transition>
            <transition
                name="animate__animated animate__bounce"
                enter-active-class="animate__fadeInUp"
                leave-active-class="animate__zoomOut"
                appear
            >
            <!-- 登录注册按钮 -->
            <div v-show="isShow" class="btnArea">
                <el-button type="success" round style="background-color: #257B5E;letter-spacing: 5px" @click="UserLogin"  >登录</el-button>
            </div>
            </transition>
        </div>
        <!-- 注册表单 -->
        <div class="registArea">
            <transition
                name="animate__animated animate__bounce"
                enter-active-class="animate__fadeInUp"
                leave-active-class="animate__zoomOut"
                appear
            >
            <!--  注册表头-->
            <div v-show="!isShow" class="rigestTitle">
                用户注册
            </div>
            </transition>
            <transition
                name="animate__animated animate__bounce"
                enter-active-class="animate__fadeInUp"
                leave-active-class="animate__zoomOut"
                appear
            >
<!--            注册表单-->
            <div  v-show="!isShow" class="registForm">
                <div style="flex: 1;display: flex;justify-content: center;align-items: center">
                  用&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;&nbsp;名:
                    <el-input
                        placeholder="请输入用户名"
                        v-model="regUser.regUsername"
                        style="width: 165px;margin-left: 10px"
                        clearable
                    >
                    </el-input>
                </div>
                <div style="flex: 1;display: flex;justify-content: center;align-items: center">
                    密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:
                    <el-input placeholder="请输入密码" style="width: 165px;margin-left: 10px" v-model="regUser.regPwd" show-password></el-input>
                </div>
                <div style="flex: 1;display: flex;justify-content: center;align-items: center;">
                    确&nbsp;认&nbsp;密&nbsp;码:
                    <el-input placeholder="请再次输入密码" style="width: 165px;margin-left: 10px" v-model="regUser.regRePwd" show-password></el-input>
                </div>
              <div style="flex: 1;display: flex;justify-content: center;align-items: center;">
                邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱:
                <el-input placeholder="请输入邮箱" style="width: 165px;margin-left: 10px" v-model="regUser.regEmail" ></el-input>
              </div>
              <div style="flex: 1;display: flex;justify-content: center;align-items: center;">
                电&nbsp;话&nbsp;号&nbsp;码:
                <el-input placeholder="请输入电话号码" style="width: 165px;margin-left: 10px" v-model="regUser.regPhone" ></el-input>
              </div>
            </div>
            </transition>
            <transition
                name="animate__animated animate__bounce"
                enter-active-class="animate__fadeInUp"
                leave-active-class="animate__zoomOut"
                appear
            >
            <!--注册按钮-->
            <div  v-show="!isShow" class="registBtn">
                <el-button type="success" round style="background-color: #257B5E;letter-spacing: 5px" @click="userRegister">注册</el-button>
            </div>
            </transition>
        </div>
        <!-- 信息展示界面 -->
         <div id="aaa" class="showInfo"
              :style="{
             borderTopRightRadius:styleObj.bordertoprightradius,
             borderBottomRightRadius:styleObj.borderbottomrightradius,
             borderTopLeftRadius:styleObj.bordertopleftradius,
             borderBottomLeftRadius:styleObj.borderbottomleftradius,
             right:styleObj.rightDis
            }"
              ref="showInfoView">

             <transition
                 name="animate__animated animate__bounce"
                 enter-active-class="animate__fadeInUp"
                 leave-active-class="animate__zoomOut"
                 appear
             >
            <!-- 没有用户输入用户名或者找不到用户名的时候 -->
            <div v-show="isShow" style="display: flex;flex-direction: column;align-items: center;justify-content: center;width: 100%;height: 100%">
             <!-- 欢迎语 -->
              <div style="flex: 2;display: flex;align-items: center;font-size: 22px;color: #FFFFFF;font-weight: bold">
                欢迎使用上海大学电子图书分享平台
              </div>
            <!-- 欢迎图片 -->
              <div style="flex: 2">
                <el-button type="success"  style="background-color:#257B5E;border: 1px solid #ffffff;" round @click="changeToRegiest">还没有账户？点击注册</el-button>
              </div>
           </div>
             </transition>
             <transition
                 name="animate__animated animate__bounce"
                 enter-active-class="animate__fadeInUp"
                 leave-active-class="animate__zoomOut"
                 appear
             >
           <!-- 用户注册的时候展示信息 -->
           <div v-show="!isShow" style="display: flex;flex-direction: column;align-items: center;justify-content: center;width: 100%;height: 100%">
               <!-- 欢迎语 -->
               <div style="flex: 2;display: flex;align-items: center;font-size: 22px;color: #FFFFFF;font-weight: bold">
                   欢迎注册
               </div>
               <!-- 欢迎图片 -->
               <div style="flex: 2">
                   <el-button type="success"  style="background-color:#257B5E;border: 1px solid #ffffff;" round @click="changeToLogin">已有账户？点击登录</el-button>
               </div>
           </div>
             </transition>
        </div>
   </div>

  </div>
</template>

<script>
import '@/assets/css/Login.css';
import {Axios as request} from "axios";
export default {
    name:'Login',
    data(){
      return{
        //登录用户提交的信息
        loginUser:{
          username:"",
          password:""
        },

        // 注册用户提交的信息
        regUser:{
          regUsername:'',
          regRePwd:'',
          regPwd:'',
          regEmail:'',
          regPhone:''
        },
        rules:{
          username:[
            {required:true, message:'请输入账号', trigger:'blur'}
          ],
          password:[
            {required:true, message:'请输入密码', trigger:'blur'}
          ]
        },
        styleObj:{
          bordertoprightradius:'15px',
          borderbottomrightradius: '15px',
          bordertopleftradius:'0px',
          borderbottomleftradius:'0px',
          rightDis:'0px'
        },
        isShow:true
      }
    },
    created() {
    },
    methods:{
      // 更改相关的样式
      changeToRegiest(){
        this.styleObj.bordertoprightradius= '0px'
        this.styleObj.borderbottomrightradius='0px'
        this.styleObj.bordertopleftradius='15px'
        this.styleObj.borderbottomleftradius='15px'
        this.styleObj.rightDis='50%'
        this.isShow = !this.isShow
      },
      changeToLogin(){
        this.styleObj.bordertoprightradius= '15px'
        this.styleObj.borderbottomrightradius='15px'
        this.styleObj.bordertopleftradius='0px'
        this.styleObj.borderbottomleftradius='0px'
        this.styleObj.rightDis='0px'
        this.isShow = !this.isShow
      },
      //用户登录
      UserLogin(){
        this.$refs['ruleForm'].validate((valid) => {
          if (valid) { //表单校验合法
            this.request.post("http://124.71.166.37:9091/users/login", this.loginUser).then(res => {
              if (res.code == "200") {
                localStorage.setItem("loguserinfo", JSON.stringify(res.data))
                this.$router.push("/home") //跳转到主界面
                this.$message.success("登陆成功！")
              } else if (res.code == "400") {
                this.$message.warning(res.msg)
              } else if (res.code == "401") {
                this.$message.error(res.msg)
              } else {
                this.$message.error("用户名或密码错误！")
              }
            })
          }
        })
      },
      //用户注册
      userRegister(){
        // 邮箱验证正则表达式
        const emailReg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+\.[a-zA-Z0-9_-]+$/;
        //电话号码正则表达式
        const phoneReg =/^(?:\+86)?1[3-9]\d{9}$|^(?:\+852)?\d{8}$/;

        if(this.regUser.regUsername===""){
          this.$message.error("用户名不能为空！")
          return false
        }else if(this.regUser.regPwd===""){
          this.$message.error("密码不能为空！")
          return false
        } else if(this.regUser.regPwd!=this.regUser.regRePwd){
          this.$message.error("两次密码输入不同，请检查后重新注册！")
          return false
        }else if(this.regUser.regEmail===""){
          this.$message.error("邮箱不能为空！")
          return false
        }else if(!emailReg.test(this.regUser.regEmail)){
          this.$message.error("邮箱不正确请检查后重新输入！")
          return false
        } else if(this.regUser.regPhone===""){
          this.$message.error("电话号码不能为空！")
          return false
        }else if(!phoneReg.test(this.regUser.regPhone)){
          this.$message.error("电话号码不正确请重新输入！")
          return false
        }
        else{
          let user = {};
          user.username = this.regUser.regUsername
          user.password = this.regUser.regPwd
          user.email=this.regUser.regEmail
          user.phone = this.regUser.regPhone
          this.request.post("http://124.71.166.37:9091/users/register",user).then(res=>{
            if(res.code==="200"){
              this.$message.success("注册成功！")
              this.changeToLogin()
            }
            if(res.code==="400"){
              this.$message.error(res.msg)
              return
            }
          })
        }

      }
    },

}
</script>

<style scoped>
.base{
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    /*background-image: url("@/assets/images/background.png");*/
    background-size: 100%;
}
.loginAndRegist{
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
    overflow: hidden;
}
.loginArea{
    border: 1px solid #000000;
    background-color: rgba(255,255,255,0.8);
    border-top-left-radius: 15px;
    border-bottom-left-radius: 15px;
    height: 400px;
    width: 350px;
    z-index: 1;
    display: flex;
    flex-direction: column;
    align-items: center;
    overflow: hidden;
}
.registArea{
    border: 1px solid #000000;
    border-top-right-radius: 15px;
    border-bottom-right-radius: 15px;
    height: 400px;
    width: 350px;
    background-color: rgba(255,255,255,0.8);
    z-index: 1;
    display: flex;
    flex-direction: column;
    justify-content:center ;
    align-items: center;
}
.showInfo{
    border-top-right-radius: 15px;
    border-bottom-right-radius: 15px;
    position: absolute;
    height: 400px;
    width: 350px;
    z-index:2;
    top: 0;
    right: 0;
    background-image: url("@/assets/images/welcome.png");
    background-size: 90%;
}
.showInfo:hover{
    background-size: 100%;
    background-position: -50px -50px;
}
.title{
    width: 70%;
    flex:1;
    border-bottom: 1px solid #257B5E;
    display: flex;
    align-items: center;
    color: #257B5E;
    font-weight: bold;
    font-size: 24px;
    display: flex;
    justify-content: center;
}
#aaa{
    transition: 0.3s linear;
}
.pwdArea{
    width: 100%;
    flex:2;
    display: flex;
    flex-direction: column;
    display: flex;
    flex-direction: column;
}
.pwdArea input{
    outline: none;
    height: 30%;
    border-radius:13px ;
    padding-left: 10px;
    font-size: 12px;
    border: 1px solid gray;
}
.pwdArea input:focus{
    border: 2px solid #257B5E;
}
.btnArea{
  flex:1;
  width: 100%;
  display: flex;
  justify-content: space-around;
  align-items: center;
}
.rigestTitle{
  width: 70%;
  flex: 1;
  color: #257B5E;
  font-weight: bold;
  font-size: 24px;
  display: flex;
  justify-content: center;
  align-items: center;
  border-bottom: 1px solid #257B5E;
}
.registForm{
  flex: 2;
  display: flex;
  flex-direction: column;
  color: #257B5E;
  font-size: 16px;
}
.registForm input{
    outline: none;
    height: 30%;
    border-radius:13px ;
    padding-left: 10px;
    font-size: 12px;
    border: 1px solid gray;
    }
.registForm input:focus{
    border: 2px solid #257B5E;
}
#elselect:focus{
    border: 2px solid #257B5E;
}
.registBtn{
        flex: 1;
        display: flex;
        justify-content: center;
        align-items: center;
    }


</style>
