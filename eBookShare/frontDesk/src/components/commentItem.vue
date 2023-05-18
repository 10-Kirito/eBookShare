<template>
  <div>






<!--    <el-card class="box-card">-->
<!--      <el-row :gutter="20">-->
<!--        <el-col :span="4">-->
<!--          <div class="grid-content bg-purple">-->
<!--            <el-avatar :size="60" :src="imagesurl">-->
<!--              <img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"/>-->
<!--            </el-avatar>-->
<!--          </div>-->
<!--        </el-col>-->

<!--        <el-col :span="20">-->
<!--          <el-row>-->
<!--            {{this.datetime}}-->
<!--          </el-row>-->

<!--          <el-row>-->
<!--            <div class="grid-content bg-purple">-->
<!--              <p>{{this.text}}</p>-->
<!--            </div>-->
<!--          </el-row>-->

<!--        </el-col>-->
<!--      </el-row>-->


<!--    </el-card>-->

  </div>
</template>

<script>
export default {
  name: "commentItem",
  props:{
    // 该变量用来存储该评论的详细信息，发表者的头像，昵称，发表时间，发表内容，点赞数量
    detailsMessage:{}
  },
  data(){
    return{
      testData:{
        bookView: "/images/books.png",
        username: "kirito",
        time: "2023年5月17日13点55分",
        text: "评论内容!",
        stars: 0
      },
      imagesurl: require("../assets/images/cutcamera.png"),
      text: "评论评论评论评论评论评论评论论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论",
      datetime: "15点51分"
    }
  }
}
</script>

<style scoped>
.box-card {
  width: 800px;
  height: 120px;
  box-shadow:
      inset 0 -3em 3em rgba(0,0,0,0.1),
      0 0  0 2px rgb(255,255,255),
      0.3em 0.3em 1em rgba(0,0,0,0.3);
  margin-bottom: 20px;
  margin-left: 30%;
}
</style>
