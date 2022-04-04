<template>
  <a-drawer
    width="45%"
    :title="formTitle"
    :label-col="4"
    :wrapper-col="14"
    :visible="open"
    :body-style="{height:'calc(100vh - 100px)',overflow:'auto'}"
    @close="cancel">
    <a-form-model ref="form" :model="form" :rules="rules" layout="vertical">
      <a-spin :spinning="spinning" :delay="delayTime" tip="Loading...">
      <a-row :gutter="32">
        <a-col :span="12">
          <a-form-model-item label="名称" prop="name">
            <a-input v-model="form.name" placeholder="请输入名称"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="区划" prop="district">
            <a-input v-model="form.district" placeholder="请输入区划"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="维度" prop="latitude">
            <a-input v-model="form.latitude" placeholder="请输入维度"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="经度" prop="longitude">
            <a-input v-model="form.longitude" placeholder="请输入经度"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="详情地址" prop="address">
            <a-input v-model="form.address" placeholder="请输入详情地址"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="区划编码" prop="districtCode">
            <a-textarea v-model="form.districtCode" placeholder="请输入区划编码" :rows="4"/>
          </a-form-model-item>
        </a-col>
    </a-row>
    <a-row :gutter="32">
        <a-col :span="12">
          <a-form-model-item label="备注" prop="remark">
            <a-textarea v-model="form.remark" placeholder="请输入备注" :rows="4"/>
          </a-form-model-item>
        </a-col>
    </a-row>
    <a-row :gutter="32">
      </a-row>
      </a-spin>
       <div class="bottom-control">
              <a-space>
              <a-button @click="cancel">
                  取消
              </a-button>
              <a-button type="primary" @click="submitForm">
                  保存
              </a-button>
              </a-space>
       </div>
    </a-form-model>
  </a-drawer>
</template>
<script>
import TShippingAddressEditForm from './TShippingAddressForm'
export default {
  ...TShippingAddressEditForm
}
</script>
