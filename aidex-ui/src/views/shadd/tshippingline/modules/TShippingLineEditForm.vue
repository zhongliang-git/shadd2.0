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
          <a-form-model-item label="发货站点" prop="shippingsiteid">
            <a-input v-model="form.shippingsiteid" placeholder="请输入发货站点"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="收货站点" prop="seceivingsiteid">
            <a-input v-model="form.seceivingsiteid" placeholder="请输入收货站点"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12" >
          <a-form-model-item label="服务类型" prop="servetype">
            <a-select
              placeholder="请选择服务类型"
              v-model="form.servetype"
              :getPopupContainer="
                triggerNode => {
                  return triggerNode.parentNode || document.body
                }
              "
              style="width: 100%"
              allow-clear>
              <a-select-option v-for="(d, index) in servetypeOptions" :key="index" :value="d.dictValue">{{ d.dictLabel }}</a-select-option>
            </a-select>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="运费" prop="freight">
            <a-input-number v-model="form.freight" :min="0" style="width: 100%"/>
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
import TShippingLineEditForm from './TShippingLineForm'
export default {
  ...TShippingLineEditForm
}
</script>
