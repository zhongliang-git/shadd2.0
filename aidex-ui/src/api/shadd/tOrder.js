import request from '@/utils/request'

// 查询订单列表
export function listTOrder (query) {
  return request({
    url: '/shadd/tOrder/list',
    method: 'get',
    params: query
  })
}

// 查询订单详细
export function getTOrder (id) {
  return request({
    url: '/shadd/tOrder/' + id,
    method: 'get'
  })
}

// 新增订单
export function addTOrder (data) {
  return request({
    url: '/shadd/tOrder',
    method: 'post',
    data: data
  })
}

// 修改订单
export function updateTOrder (data) {
  return request({
    url: '/shadd/tOrder',
    method: 'put',
    data: data
  })
}

// 删除订单
export function delTOrder (id) {
  return request({
    url: '/shadd/tOrder/' + id,
    method: 'delete'
  })
}

// 导出订单
export function exportTOrder (query) {
  return request({
    url: '/shadd/tOrder/export',
    method: 'get',
    params: query
  })
}
