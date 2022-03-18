import request from '@/utils/request'

// 查询站点列表
export function listTSite (query) {
  return request({
    url: '/shadd/tSite/list',
    method: 'get',
    params: query
  })
}

// 查询站点详细
export function getTSite (id) {
  return request({
    url: '/shadd/tSite/' + id,
    method: 'get'
  })
}

// 新增站点
export function addTSite (data) {
  return request({
    url: '/shadd/tSite',
    method: 'post',
    data: data
  })
}

// 修改站点
export function updateTSite (data) {
  return request({
    url: '/shadd/tSite',
    method: 'put',
    data: data
  })
}

// 删除站点
export function delTSite (id) {
  return request({
    url: '/shadd/tSite/' + id,
    method: 'delete'
  })
}

// 校验站点编码是否存在
export function checkCodeUnique (id, code) {
  if (id === undefined) {
    id = ''
  }
  return request({
    url: '/shadd/tSite/checkCodeUnique/' + code + '/' + id,
    method: 'get'
  })
}

// 导出站点
export function exportTSite (query) {
  return request({
    url: '/shadd/tSite/export',
    method: 'get',
    params: query
  })
}
