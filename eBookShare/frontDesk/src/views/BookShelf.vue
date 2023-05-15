<template>
  <div style="width: 60%; margin-left: 420px" >
    <!-- 图书信息展示-->
    <el-row v-for="(row ,index1) in rowCount" :key="row" style="margin-bottom: 30px">
        <el-col :span="6" v-for="(col, index2) in colCount" :key="col" >
          <book-from-shelf v-if="dataload" :book-info="booksData[(row - 1) * colCount + col - 1]" style="width: 270px; height: 420px"></book-from-shelf>
        </el-col>
    </el-row>


    <!-- 分页查询选项-->
    <el-row style="margin-top: 60px">
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
  name: "BookShelf",
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

</style>
