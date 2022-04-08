import request from '@/utils/request'

// 查询订单明细子表列表
export function listTOrderDetail (query) {
  return request({
    url: '/shadd/tOrderDetail/list',
    method: 'get',
    params: query
  })
}

// 查询订单明细子表详细
export function getTOrderDetail (id) {
  return request({
    url: '/shadd/tOrderDetail/' + id,
    method: 'get'
  })
}

// 新增订单明细子表
export function addTOrderDetail (data) {
  return request({
    url: '/shadd/tOrderDetail',
    method: 'post',
    data: data
  })
}

// 修改订单明细子表
export function updateTOrderDetail (data) {
  return request({
    url: '/shadd/tOrderDetail',
    method: 'put',
    data: data
  })
}

// 删除订单明细子表
export function delTOrderDetail (id) {
  return request({
    url: '/shadd/tOrderDetail/' + id,
    method: 'delete'
  })
}

// 导出订单明细子表
export function exportTOrderDetail (query) {
  return request({
    url: '/shadd/tOrderDetail/export',
    method: 'get',
    params: query
  })
}
