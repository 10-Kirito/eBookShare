<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入用户名" suffix-icon="el-icon-search" v-model="username"></el-input>
      <el-input style="width: 200px" placeholder="请输入电子邮箱" class="ml-5" suffix-icon="el-icon-message" v-model="email"></el-input>
      <el-input style="width: 200px" placeholder="请输入电话号码" class="ml-5" suffix-icon="el-icon-message" v-model="phone"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>

    <div style="margin: 10px 0">
      <el-button type="primary" @click="exp" class="ml-5">导出<i class = "el-icon-top"></i></el-button>
    </div>

    <el-table :data="tableData" border stripe header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
      <el-table-column prop="username" label="用户名" >
      </el-table-column>
      <el-table-column prop="email" label="电子邮箱" >
      </el-table-column>
      <el-table-column prop="phone" label="电话号码" >
      </el-table-column>
      <el-table-column label="操作" width="357">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)" style="margin-left: 40px">编辑<i class="el-icon-edit"></i></el-button>
          <el-button type="danger" @click="handleEdit2(scope.row)">重置密码<i class="el-icon-edit"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text="确认"
              cancel-button-text="取消"
              icon="el-icon-info"
              icon-color="#626AEF"
              title="是否删除?"
              @confirm="del(scope.row.id)"
          >
            <template #reference>
              <el-button type="danger">删除<i class="el-icon-remove-outline"></i></el-button>
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
        <el-form-item label="用户名">
          <el-input v-model="form.username" autocomplete="off" />
        </el-form-item>
        <el-form-item label="电子邮箱">
          <el-input v-model="form.email" autocomplete="off" />
        </el-form-item>
        <el-form-item label="电话号码">
          <el-input v-model="form.phone" autocomplete="off" />
        </el-form-item>
      </el-form>
      <span>
        <el-button style="margin-left: 40%"  @click="dialogFormVisible = false" >取消</el-button>
        <el-button type="primary"  @click="save">确认</el-button>
      </span>

    </el-dialog>

    <!--    新增用户  弹出的界面-->
    <el-dialog title="书籍信息" :visible.sync="UserFormVisible" width="30%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="用户名">
          <el-input v-model="form.username" autocomplete="off" />
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.password" autocomplete="off" />
        </el-form-item>
        <el-form-item label="电子邮箱">
          <el-input v-model="form.email" autocomplete="off" />
        </el-form-item>
        <el-form-item label="电话号码">
          <el-input v-model="form.phone" autocomplete="off" />
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="UserFormVisible = false">取消</el-button>
        <el-button type="primary" @click="save">
          确认
        </el-button>
      </span>
      </template>
    </el-dialog>
  </div>

</template>

<script>
import EBookUpload from "@/views/Admins/EBookUpload.vue";
import { MD5 } from 'crypto-js';
export default {
  name: "User",
  computed: {
    EBookUpload() {
      return EBookUpload
    }
  },
  data(){
    return{
      ruleForm: {
        pass: '',
        checkPass: ''
      },
      dialogFormVisible2:false,
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,

      id: "",
      username: "",
      email: "",
      phone: "",
      password: "",
      currentsize: "",
      dialogFormVisible: false,
      UserFormVisible: false,
      multipleSelection: [],
      form: {},
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
      //通过request.js中的baseurl已经将前面的http://124.71.166.37:9091部分省略了
      this.request.get("/users/page",{
        params:{
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username,
          email: this.email,
          phone: this.phone,

        }
      })
          .then(res => {
            console.log(res)
            this.tableData = res.records
            this.total = res.total
          })

    },
    openDialog(){
      this.dialogFormVisible2=true
    },

    // 前端将密码进行一个加密：
    encryptPassword(password){
      password = "123456";
      const  enctypotedPassword = MD5(password).toString();
      return enctypotedPassword;
    },
    // 前端修改密码接口实现：
    savePassword(){
        this.form.password=this.ruleForm.pass
        //md5加密
        // this.$message.success("加密前的密码"+this.form.password);
        this.form.password = this.encryptPassword(this.form.password);
        // this.$message.success("加密后的密码"+this.form.password);
        // this.$message.success("当前用户id"+this.form.id);
        this.request.post("http://localhost:9091/users/changepassword",this.form).then(res=>{
          if(res){
            this.$message.success("保存成功")
            this.dialogFormVisible=false
          }else {
            this.$message.error("保存失败")
          }
        })
    },
    save(){
      //发送数据到后端
      this.request.post("/users/save",this.form)
          .then(res => {
            if(res){
              this.$message.success("保存成功")
              this.dialogFormVisible = false
              this.UserFormVisible = false
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
    // 重置密码，调用方法savePassword,修改用户密码为123456
    handleEdit2(row){
      this.form = row //将数据赋予弹窗
      this.savePassword();
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
    reset(){
      this.email = ""
      this.username = ""
      this.phone = ""
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
      window.open("/users/export")
    },
    del(id){
      this.request.delete("/users/" + id).then(res => {
        if(res.code === '200'){
          this.$message.success("删除成功")
          this.load()
        }else {
          this.$message.error("删除失败")
        }
      })
    },
    handleExcelImportSuccess(){
      this.$message.success("文件上传成功！")
      this.load()
    }
  }
}
</script>

<style>
.headerBg{
  background: #ccc !important;
}
</style>
