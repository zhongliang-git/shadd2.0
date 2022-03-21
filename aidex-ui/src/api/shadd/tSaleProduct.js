import request from '@/utils/request'

// 查询销售产品列表
export function listTSaleProduct (query) {
  return request({
    url: '/shadd/tSaleProduct/list',
    method: 'get',
    params: query
  })
}

// 查询销售产品详细
export function getTSaleProduct (id) {
  return request({
    url: '/shadd/tSaleProduct/' + id,
    method: 'get'
  })
}

// 新增销售产品
export function addTSaleProduct (data) {
  return request({
    url: '/shadd/tSaleProduct',
    method: 'post',
    data: data
  })
}

// 修改销售产品
export function updateTSaleProduct (data) {
  return request({
    url: '/shadd/tSaleProduct',
    method: 'put',
    data: data
  })
}

// 删除销售产品
export function delTSaleProduct (id) {
  return request({
    url: '/shadd/tSaleProduct/' + id,
    method: 'delete'
  })
}

// 导出销售产品
export function exportTSaleProduct (query) {
  return request({
    url: '/shadd/tSaleProduct/export',
    method: 'get',
    params: query
  })
}
