local ret={}

local qm = cjson.decode(ARGV[1])

local check = function (uuid,name,imgPath,description)
	if qm.uuid~=nil and tonumber(qm.uuid) > 0 then
   		if tonumber(qm.uuid) ~= tonumber(uuid) then
   			return false
   		end
	end
	
	if qm.name~=nil and string.len(qm.name) > 0 then
		if string.find(name,qm.name) == nil then
			return false
		end
	end
	
	if qm.imgPath~=nil and string.len(qm.imgPath) > 0 then
		if string.find(imgPath,qm.imgPath) == nil then
			return false
		end
	end
	
	if qm.description~=nil and string.len(qm.description) > 0 then
		if string.find(description,qm.description) == nil then
			return false
		end
	end

	return true
end


--1:获取所有的goods uuid
local goodsUuids = redis.call("lrange","GoodsUuidsL",0,-1) 

local count = 0
local start = 0
local endCount = 1

if tonumber(qm.page.nowPage) >= 1 then
	start = (tonumber(qm.page.nowPage) - 1) * tonumber(qm.page.pageShow)
	endCount = start + tonumber(qm.page.pageShow)
end

if tonumber(qm.page.nowPage) < 1 then
	start = 0
	endCount = tonumber(qm.page.pageShow)
end


for i=1,#goodsUuids do
	--2：获取每个goods 对象	
	local uuid = redis.call("hget","GoodsH:"..goodsUuids[i],"uuid")
	local name = redis.call("hget","GoodsH:"..goodsUuids[i],"name")
	local imgPath = redis.call("hget","GoodsH:"..goodsUuids[i],"imgPath")
	local description = redis.call("hget","GoodsH:"..goodsUuids[i],"description")
	
	--3：判断每个goods 对象是否满足条件
	if check(uuid,name,imgPath,description) then
		
		count = count + 1		
		
		--4：满足条件的对象就存放到要返回的集合中
		--还需要满足分页的条件
		if count > start and count <= endCount then
			local gm ={
				uuid=uuid,
				name=name,
				imgPath=imgPath,
				description=description
			}
			
			table.insert(ret,cjson.encode(gm))
		end	
	end
end

table.insert(ret,"{totalCount="..count.."}")

return ret