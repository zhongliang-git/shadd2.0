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
          <a-form-model-item label="铁路运线" prop="lineid">
            <a-select
              show-search
              placeholder="请选择铁路运线"
              @blur="cleanSearchMess"
              :filter-option="setSearchMess"
              @inputKeydown="e => enterKeydown(e)"
              :value="form.lineid"
              v-model="form.lineid"
            >
              <a-select-option
                v-for="(d, index) in this.lineList"
                :key="index"
                :value="d.id">
                {{ d.name + "(" + d.code + ")" }}
              </a-select-option>
            </a-select>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="到货数量" prop="number">
            <a-input-number v-model="form.number" :min="0" style="width: 100%"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12" >
          <a-form-model-item label="运输状态" prop="billstatus">
            <a-select
              placeholder="请选择运输状态"
              v-model="form.billstatus"
              :disabled="form.billstatus === '到达'"
              :getPopupContainer="
                triggerNode => {
                  return triggerNode.parentNode || document.body
                }
              "
              style="width: 100%"
              allow-clear>
              <a-select-option v-for="(d, index) in billstatusOptions" :key="index" :value="d.dictValue">{{ d.dictLabel }}</a-select-option>
            </a-select>
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
import TGoodsTransportEditForm from './TGoodsTransportForm'
export default {
  ...TGoodsTransportEditForm
}
</script>
