import request from '@/utils/request'

// 查询库存子表列表
export function listTRepertory (query) {
  return request({
    url: '/shadd/tRepertory/list',
    method: 'get',
    params: query
  })
}

// 查询库存子表详细
export function getTRepertory (id) {
  return request({
    url: '/shadd/tRepertory/' + id,
    method: 'get'
  })
}

// 新增库存子表
export function addTRepertory (data) {
  return request({
    url: '/shadd/tRepertory',
    method: 'post',
    data: data
  })
}

// 修改库存子表
export function updateTRepertory (data) {
  return request({
    url: '/shadd/tRepertory',
    method: 'put',
    data: data
  })
}

// 删除库存子表
export function delTRepertory (id) {
  return request({
    url: '/shadd/tRepertory/' + id,
    method: 'delete'
  })
}

// 导出库存子表
export function exportTRepertory (query) {
  return request({
    url: '/shadd/tRepertory/export',
    method: 'get',
    params: query
  })
}
