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
          <a-form-model-item label="厂家" prop="factoryid">
            <a-input v-model="form.factoryid" placeholder="请输入厂家"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="产品" prop="productid">
            <a-input v-model="form.productid" placeholder="请输入产品"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="出厂价" prop="exw">
            <a-input-number v-model="form.exw" :min="0" style="width: 100%"/>
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
import TPurchaseAddForm from './TPurchaseForm'
export default {
  ...TPurchaseAddForm
}
</script>
