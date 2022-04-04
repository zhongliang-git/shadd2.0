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
    <a-col :span="12" >
        <a-form-model-item label="客户" prop="consumerId">
            <select-user
                    v-model="form.consumerId"
                    select-model="single"
            />
        </a-form-model-item>
    </a-col>
        <a-col :span="12">
          <a-form-model-item label="充值金额" prop="payMoney">
            <a-input-number v-model="form.payMoney" :min="0" style="width: 100%"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="赠送金额" prop="donateMoney">
            <a-input-number v-model="form.donateMoney" :min="0" style="width: 100%"/>
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
import TPayRecordAddForm from './TPayRecordForm'
export default {
  ...TPayRecordAddForm
}
</script>
