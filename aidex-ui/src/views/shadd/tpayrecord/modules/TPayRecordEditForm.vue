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
import TPayRecordEditForm from './TPayRecordForm'
export default {
  ...TPayRecordEditForm
}
</script>
