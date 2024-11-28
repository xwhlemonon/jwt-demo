<template>
  <div style="padding: 30px; width: 50%; margin: 10px auto;">
    <el-form>
      <el-form-item label="账号">
        <el-input v-model="formData.username"/>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="formData.password"/>
      </el-form-item>
      <el-form-item>
        <el-button style="margin: 0 auto;" @click="login">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
  <div style="margin-top: 50px;">
    <el-button @click="select">查询</el-button>
    <div>{{ userData }}</div>
  </div>
</template>


<script setup>
import {ref} from "vue";
import {ElMessage} from "element-plus";

const formData = ref({username: "", password: ""});
const userData = ref("");

const login = () => {
  axios.post("/v1/user/login", formData.value)
      .then(res => {
        ElMessage.success("认证成功");
      });
};

const select = () => {
  axios.get("/v1/user/select",)
      .then(res => {
        userData.value = JSON.stringify(res);
      });
};

</script>


<style scoped>

</style>
