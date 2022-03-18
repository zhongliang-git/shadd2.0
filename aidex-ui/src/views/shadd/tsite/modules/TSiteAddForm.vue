<template>
  <ant-modal
    modalWidth="800"
    modalHeight="550"
    :visible="open"
    :modal-title="formTitle"
    :adjust-size="true"
    @cancel="cancel"
  >
    <a-form-model ref="form" :model="form" :rules="rules" slot="content" layout="vertical">
      <a-row :gutter="32">
        <a-col :span="12">
          <a-form-model-item label="站点名称" prop="name">
            <a-input v-model="form.name" placeholder="请输入站点名称"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="站点编码" prop="code">
            <a-input v-model="form.code" placeholder="请输入站点编码"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="区划" prop="zoning">
            <a-input v-model="form.zoning" placeholder="请输入区划"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="备注" prop="remark">
            <a-textarea v-model="form.remark" placeholder="请输入备注" :rows="4"/>
          </a-form-model-item>
        </a-col>
    </a-row>
    <a-row :gutter="32">
      </a-row>
    </a-form-model>
    <template slot="footer">
      <a-button @click="cancel">
        取消
      </a-button>
      <a-button type="primary" @click="submitForm">
        保存
      </a-button>
    </template>
  </ant-modal>
</template>
<script>
import TSiteAddForm from './TSiteForm'
export default {
  ...TSiteAddForm
}
</script>
