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
        <a-form-model-item label="客户" prop="userId">
            <select-user
                    v-model="form.userId"
                    select-model="single"
            />
        </a-form-model-item>
    </a-col>
        <a-col :span="12">
          <a-form-model-item label="收货地址" prop="shippingaddress">
            <a-input v-model="form.shippingaddress" placeholder="请输入收货地址"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="配送日期" prop="dispatchingdate">
            <a-date-picker
              v-model="form.dispatchingdate"
              valueFormat="YYYY-MM-DD"
              :show-today="true"
              placeholder="选择日期"
            />
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="总金额" prop="total">
            <a-input v-model="form.total" placeholder="请输入总金额"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12" >
          <a-form-model-item label="状态" prop="billStatus">
            <a-radio-group v-model="form.billStatus" button-style="solid">
              <a-radio-button
                v-for="(dict, index) in billstatusOptions"
                :key="index"
                :value="dict.dictValue"
              >
                {{ dict.dictLabel }}
              </a-radio-button>
            </a-radio-group>
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
import TOrderAddForm from './TOrderForm'
export default {
  ...TOrderAddForm
}
</script>
