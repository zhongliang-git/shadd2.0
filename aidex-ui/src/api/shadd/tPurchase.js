import request from '@/utils/request'

// 查询采购产品列表
export function listTPurchase (query) {
  return request({
    url: '/shadd/tPurchase/list',
    method: 'get',
    params: query
  })
}

// 查询采购产品详细
export function getTPurchase (id) {
  return request({
    url: '/shadd/tPurchase/' + id,
    method: 'get'
  })
}

// 新增采购产品
export function addTPurchase (data) {
  return request({
    url: '/shadd/tPurchase',
    method: 'post',
    data: data
  })
}

// 修改采购产品
export function updateTPurchase (data) {
  return request({
    url: '/shadd/tPurchase',
    method: 'put',
    data: data
  })
}

// 删除采购产品
export function delTPurchase (id) {
  return request({
    url: '/shadd/tPurchase/' + id,
    method: 'delete'
  })
}

// 导出采购产品
export function exportTPurchase (query) {
  return request({
    url: '/shadd/tPurchase/export',
    method: 'get',
    params: query
  })
}
