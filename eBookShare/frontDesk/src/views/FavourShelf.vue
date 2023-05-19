<template>
  <div style="width: 90%; margin-left: 5%" class="header-with-background">
    <!-- 图书信息展示-->
    <el-row :gutter="24" v-for="(row ,index1) in rowCount" v-if="booksData[(row - 1) * colCount]" :key="row" style="margin-bottom: 30px;min-width: 1200px">
      <el-col :span="6" v-for="(col, index2) in colCount" :key="col" style="width: 300px">
        <book-from-shelf  v-if="dataload" :bookInfo="booksData[(row - 1) * colCount + col - 1]" style="width: 100%;"></book-from-shelf>
      </el-col>
    </el-row>


    <!-- 分页查询选项-->
    <el-row style="margin-top: 10px">
      <div >
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page.sync="currentPage"
            :page-size="pageSize"
            layout="prev, pager, next, jumper"
            :total="total">
        </el-pagination>
      </div>
    </el-row>
  </div>
</template>

<script>

import bookFromShelf from "@/components/bookFromShelf.vue";

export default {
  name: "FavourShelf",
  components:{
    bookFromShelf
  },
  data(){
    return{
      // 设置书架的行数和列数
      rowCount: 3,
      colCount: 4,

      // 分页查询，每一页的个数 = rowCount * colCount
      currentPage: 1,
      pageSize: this.rowCount * this.colCount,
      total: 100,


      // 该用户个人书架中的图书信息
      booksData: [],

      dataload: false
    }
  },
  beforeMount() {
    this.getAllBooksData();
  },
  methods:{
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageSize = val;
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.currentPage = val;
    },
    getAllBooksData(){
      this.request.get("/books").then(res => {
        this.booksData = res;
        console.log(this.booksData);
        this.dataload = true;
      })
    }
  }
}
</script>

<style scoped>
.header-with-background {
  background-image: url('~@/assets/images/wooden.jpg');
  background-size: cover;
}
</style>
