<template>
  <div style="width: 60%; margin-left: 450px" >
    <!-- 图书信息展示-->
    <el-row v-for="(row ,index1) in rowCount" :key="row" style="margin-bottom: 20px">
        <el-col :span="6" v-for="(col, index2) in colCount" :key="col" >
          <book-from-shelf style="width: 300px; height: 450px"></book-from-shelf>
        </el-col>
    </el-row>


    <!-- 分页查询选项-->
    <el-row>
      <div class="block">
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
      total: 100
    }
  },
  methods:{
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageSize = val;
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.currentPage = val;
    }
  }
}
</script>

<style scoped>

</style>
