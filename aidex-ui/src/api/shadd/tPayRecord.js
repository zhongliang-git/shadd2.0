import request from '@/utils/request'

// 查询充值记录列表
export function listTPayRecord (query) {
  return request({
    url: '/shadd/tPayRecord/list',
    method: 'get',
    params: query
  })
}

// 查询充值记录详细
export function getTPayRecord (id) {
  return request({
    url: '/shadd/tPayRecord/' + id,
    method: 'get'
  })
}

// 新增充值记录
export function addTPayRecord (data) {
  return request({
    url: '/shadd/tPayRecord',
    method: 'post',
    data: data
  })
}

// 修改充值记录
export function updateTPayRecord (data) {
  return request({
    url: '/shadd/tPayRecord',
    method: 'put',
    data: data
  })
}

// 删除充值记录
export function delTPayRecord (id) {
  return request({
    url: '/shadd/tPayRecord/' + id,
    method: 'delete'
  })
}

// 导出充值记录
export function exportTPayRecord (query) {
  return request({
    url: '/shadd/tPayRecord/export',
    method: 'get',
    params: query
  })
}
