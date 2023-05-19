<template>
  <div style="width: 1000px; margin:16px auto">
    <div v-for="(item,i) in commentsList" :key="i" class="author-title reply-father">
      <el-avatar class="header-img" :size="40" :src="imagesurl"></el-avatar>
      <div class="author-info">
        <span class="author-name">{{item.username}}</span>
        <span class="author-time">{{item.time}}</span>
      </div>
      <div class="talk-box">
        <p>
          <span class="reply">{{item.text}}</span>
        </p>
      </div>
    </div>

    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[2,5,10,20]"
        :page-size="pageSize"
        layout="total, prev, pager, next, jumper"
        :total="total">
    </el-pagination>
    <div>
        <comment-item v-for="item in 5" :key="item"></comment-item>
    </div>
  </div>
</template>

<script>
import commentItem from "@/components/commentItem.vue";

export default  {
  name: "comments",
  props:{
    bookDetail:{},
    key:""
  },
  components:{
    // commentItem
  },
  data(){
    return{
      commentsList:[],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      imagesurl: require("../assets/images/cutcamera.png")
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      this.request.get("/commits/findcommits",{
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          bookid:this.bookDetail.bookid
        }
      }).then(res=>{
        if(res.code==='200'){
          this.commentsList=res.data.records           //根据后台数据更改
          this.total=res.data.total
          // this.$message.success("获取评论成功")
        }else {
          // this.$message.error("获取失败！")
        }
      })
    },
    handleSizeChange(pageSize){
      this.pageSize=pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      this.pageNum=pageNum
      this.load()
    },
  }
}
</script>

<style lang="scss" scoped>

.author-title:not(:last-child) {
   border-bottom: 1px solid rgba(178, 186, 194, 0.3);
}

.author-title {
  padding: 10px;
  text-align: left;
  .header-img {
    display: inline-block;
    vertical-align: top;
  }

  .author-info {
    display: inline-block;
    margin-left: 5px;
    width: 60%;
    height: 50px;
    line-height: 20px;

    > span {
      display: block;
      cursor: pointer;
      overflow: hidden;
      white-space: nowrap;
      text-overflow: ellipsis;
    }

    .author-name {
      color: #000;
      font-size: 18px;
      font-weight: bold;
      width: 10%;
    }

    .author-time {
      font-size: 14px;
      width: 25%;
    }
  }
}

.talk-box {
  margin: 0 50px;
  text-align: left;
  > p {
    margin: 0;
  }

  .reply {
    font-size: 16px;
    color: #000;
  }
}
</style>
