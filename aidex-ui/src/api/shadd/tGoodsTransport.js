import request from '@/utils/request'

// 查询货物运输子表列表
export function listTGoodsTransport (query) {
  return request({
    url: '/shadd/tGoodsTransport/list',
    method: 'get',
    params: query
  })
}

// 查询货物运输子表详细
export function getTGoodsTransport (id) {
  return request({
    url: '/shadd/tGoodsTransport/' + id,
    method: 'get'
  })
}

// 新增货物运输子表
export function addTGoodsTransport (data) {
  return request({
    url: '/shadd/tGoodsTransport',
    method: 'post',
    data: data
  })
}

// 修改货物运输子表
export function updateTGoodsTransport (data) {
  return request({
    url: '/shadd/tGoodsTransport',
    method: 'put',
    data: data
  })
}

// 删除货物运输子表
export function delTGoodsTransport (id) {
  return request({
    url: '/shadd/tGoodsTransport/' + id,
    method: 'delete'
  })
}

// 导出货物运输子表
export function exportTGoodsTransport (query) {
  return request({
    url: '/shadd/tGoodsTransport/export',
    method: 'get',
    params: query
  })
}
