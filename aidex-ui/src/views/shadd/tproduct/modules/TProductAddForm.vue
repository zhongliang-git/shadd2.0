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
          <a-form-model-item label="矿种" prop="minerals">
            <a-input v-model="form.minerals" placeholder="请输入矿种"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="规格型号" prop="specification">
            <a-input v-model="form.specification" placeholder="请输入规格型号"/>
          </a-form-model-item>
        </a-col>
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
import TProductAddForm from './TProductForm'
export default {
  ...TProductAddForm
}
</script>
