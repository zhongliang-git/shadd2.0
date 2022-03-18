import request from '@/utils/request'

// 查询厂家报价子表列表
export function listTOffer (query) {
  return request({
    url: '/shadd/tOffer/list',
    method: 'get',
    params: query
  })
}

// 查询厂家报价子表详细
export function getTOffer (id) {
  return request({
    url: '/shadd/tOffer/' + id,
    method: 'get'
  })
}

// 新增厂家报价子表
export function addTOffer (data) {
  return request({
    url: '/shadd/tOffer',
    method: 'post',
    data: data
  })
}

// 修改厂家报价子表
export function updateTOffer (data) {
  return request({
    url: '/shadd/tOffer',
    method: 'put',
    data: data
  })
}

// 删除厂家报价子表
export function delTOffer (id) {
  return request({
    url: '/shadd/tOffer/' + id,
    method: 'delete'
  })
}

// 导出厂家报价子表
export function exportTOffer (query) {
  return request({
    url: '/shadd/tOffer/export',
    method: 'get',
    params: query
  })
}
