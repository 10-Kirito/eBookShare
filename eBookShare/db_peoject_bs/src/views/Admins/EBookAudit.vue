<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入ISBN" suffix-icon="el-icon-search" v-model="isbn"></el-input>
      <el-input style="width: 200px" placeholder="请输入书籍名称" class="ml-5" suffix-icon="el-icon-message" v-model="bookname"></el-input>
      <el-input style="width: 200px" placeholder="请输入作者" class="ml-5" suffix-icon="el-icon-message" v-model="author"></el-input>
      <el-input style="width: 200px" placeholder="请输入出版社" class="ml-5" suffix-icon="el-icon-message" v-model="publisher"></el-input>
<!--      <el-input style="width: 200px" placeholder="请输入书籍名称" class="ml-5" suffix-icon="el-icon-message" v-model="tnumber"></el-input>-->
<!--      <el-input style="width: 200px" placeholder="请输入地址" class="ml-5" suffix-icon="el-icon-position" v-model="lcredit"></el-input>-->
<!--      <el-input style="width: 200px" placeholder="请输入开课学院号" class="ml-5" suffix-icon="el-icon-position" v-model="lcollege"></el-input>-->
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>

    <div style="margin: 10px 0">
<!--      <el-button type="primary" @click="handleAdd">新增<i class="el-icon-circle-plus-outline"></i></el-button>-->
<!--      <el-popconfirm-->
<!--          class="ml-5"-->
<!--          confirm-button-text="确认"-->
<!--          cancel-button-text="取消"-->
<!--          :icon="InfoFilled"-->
<!--          icon-color="#626AEF"-->
<!--          title="是否批量删除?"-->
<!--          @confirm="delBatch"-->
<!--          @cancel="cancelEvent"-->
<!--      >-->
<!--        <el-button type="danger" slot="reference">批量删除<i class = "el-icon-remove-outline"></i></el-button>-->
<!--      </el-popconfirm>-->
<!--      <el-upload action="http://localhost:9090/books/upload" :show-file-list="false" accept="xlsx" :on-success="handleExcelImportSuccess" style="display: inline-block">-->
<!--        <el-button type="primary" class="ml-5">导入<i class = "el-icon-bottom"></i></el-button>-->
<!--      </el-upload>-->

        <el-button type="primary" class="ml-5" @click="EBookup">导入<i class = "el-icon-bottom"></i></el-button>

      <el-button type="primary" @click="exp" class="ml-5">导出<i class = "el-icon-top"></i></el-button>
    </div>

    <el-table :data="tableData" border stripe header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
      <!--          多选框-->
<!--      <el-table-column type="selection" width="55" />-->
      <el-table-column prop="isbn" label="ISBN" width="120">
      </el-table-column>
      <el-table-column prop="bookname" label="书籍名称" width="150">
      </el-table-column>
      <el-table-column prop="author" label="作者">
      </el-table-column>
      <el-table-column prop="publisher" label="出版社">
      </el-table-column>
      <el-table-column prop="category" label="类别">
      </el-table-column>
      <el-table-column prop="releasetime" label="上传时间">
      </el-table-column>
      <el-table-column label="操作" width="330">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">预览<i class="el-icon-edit"></i></el-button>
          <el-button type="success" @click="handleEdit(scope.row)">编辑<i class="el-icon-edit"></i></el-button>
          <el-button type="success" @click="Pass(scope.row.bookid)">通过<i class="el-icon-edit"></i></el-button>

          <el-popconfirm
              class="ml-7"
              confirm-button-text="确认"
              cancel-button-text="取消"
              :icon="InfoFilled"
              icon-color="#626AEF"
              title="是否撤回?"
              @confirm="cancelbooks(scope.row.bookid)"
              @cancel="cancelEvent"
          >
            <template #reference>
              <el-button type="danger">撤回<i class="el-icon-remove-outline"></i></el-button>
            </template>
          </el-popconfirm>

        </template>
      </el-table-column>

    </el-table>
    <div style="padding: 10px 0" >
      <el-pagination
          :current-page="pageNum"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />

    </div>
<!--    编辑  弹出的界面-->
    <el-dialog title="书籍信息" :visible.sync="dialogFormVisible" width="30%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="ISBN">
          <el-input v-model="form.isbn" autocomplete="off" />
        </el-form-item>
        <el-form-item label="书籍名称">
          <el-input v-model="form.bookname" autocomplete="off" />
        </el-form-item>
        <el-form-item label="作者">
          <el-input v-model="form.author" autocomplete="off" />
        </el-form-item>
        <el-form-item label="出版社">
          <el-input v-model="form.publisher" autocomplete="off" />
        </el-form-item>
        <el-form-item label="类别">
          <el-input v-model="form.category" autocomplete="off" />
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="save">
          确认
        </el-button>
      </span>
      </template>
    </el-dialog>


    <!--    撤回  弹出的界面-->
    <el-dialog title="撤回通知" :visible.sync="cancel" width="30%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="原因" >
          <el-input type="textarea" v-model="suggestions" rows="6" autocomplete="off" placeholder="请输入撤回原因"/>
        </el-form-item>

      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="cancel = false">取消</el-button>
        <el-button type="primary" @click="cancelOperation">
          确认
        </el-button>
      </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import EBookUpload from "@/views/Admins/EBookUpload.vue";

export default {
  name: "User",
  computed: {
    EBookUpload() {
      return EBookUpload
    }
  },
  data(){
    return{
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,

      id: "",
      bookname: "",
      author: "",
      publisher: "",
      isbn: "",
      currentsize: "",
      suggestions:"",
      dialogFormVisible: false,
      cancel: false,
      multipleSelection: [],
      form: {},
      bookid: "",
      headerBg: 'headerBg'

    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      //请求分页查询数据
      //通过axios向后台请求参数

      //通过request.js中的baseurl已经将前面的http://localhost:9090部分省略了
      this.request.get("/auditbooks/page",{
        params:{
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          bookname: this.bookname,
          author: this.author,
          publisher: this.publisher,
          isbn: this.isbn,

        }
      })
          .then(res => {
            console.log(res)
            this.tableData = res.records
            this.total = res.total
          })

    },

    save(){
      //发送数据到后端
      this.request.post("/auditbooks/save",this.form)
          .then(res => {
            if(res){
              this.$message.success("保存成功")
              this.dialogFormVisible = false
              this.load()
            }else {
              this.$message.error("保存失败")
            }
          })
    },
    handleEdit(row){
      this.form = row //将数据赋予弹窗
      this.dialogFormVisible = true //显示弹窗

    },
    Pass(bookid){
      this.request.delete("/auditbooks/audit/" + bookid).then(res => {
        if(res.code === '200'){
          this.$message.success("审核通过")
          this.load()
        }else {
          this.$message.error("操作失败")
        }
      })
    },
    handleAdd(){
      this.dialogFormVisible = true
      this.form = {}
    },

    handleSelectionChange(val){
      console.log(val)
      this.multipleSelection = val
    },
    EBookup(){
      this.$router.push("/admins/ebookupload")
    },
    // delBatch(){
    //   let ids = this.multipleSelection.map(v => v.lnumber)   //把一个对象的数组变成一个纯数组
    //   this.request.post("/department/del/batch",ids).then(res => {
    //     if(res){
    //       this.$message.success("批量删除成功")
    //       this.load()
    //     }else {
    //       this.$message.error("批量删除失败")
    //     }
    //   })
    // },
    reset(){
      this.lnumber = ""
      this.lname = ""
      this.lcredit = ""
      this.lcollege = ""
      this.load()
    },
    handleSizeChange(pageSize){
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      this.pageNum = pageNum
      this.load()
    },
    exp(){
      window.open("http://localhost:9090/auditbooks/export")
    },
    cancelbooks(bookid){
      this.cancel = true
      this.bookid = bookid;

    },
    cancelOperation(){
      this.request.delete("/auditbooks/cancel",{
        params:{
          bookid: this.bookid,
          suggestions: this.suggestions,
        }
      }).then(res => {
        if(res.code === '200'){
          this.$message.success("撤回成功")
          this.load()
          this.cancel = false;
        }else {
          this.$message.error("撤回失败")
        }
      })
    },
    handleExcelImportSuccess(){
      this.$message.success("文件上传成功！")
      this.load()
      this.cancel = false
    }
  }
}
</script>

<style>
.headerBg{
  background: #ccc !important;
}
</style>
