<template>
  <div>
    <comment-item v-for="item in 5"></comment-item>
  </div>
</template>

<script>
import commentItem from "@/components/commentItem.vue";

export default {
  name: "comments",
  props:{
    commentsList:[]
  },
  components:{
    commentItem
  },
  data(){
    return{
      comment: ""
    }
  }
}
</script>

<style scoped>
.el-input{
  width: 700px;

}
</style>
