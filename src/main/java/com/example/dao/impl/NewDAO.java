package com.example.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.example.dao.INewDAO;
import com.example.mapper.NewMapper;
import com.example.model.NewsModel;
import com.example.paging.Pageble;

public class NewDAO extends AbstractDAO<NewsModel> implements INewDAO{
	
	@Override
	public List<NewsModel> findByCategoryId(Long categoryId) {
		String sql = "SELECT * FROM news WHERE categoryid = ?";
		return query(sql, new NewMapper(), categoryId);
	}

	@Override
	public Long save(NewsModel newmodel) {
		String sql = "INSERT INTO news (title, content, categoryid, thumbnail, shortdescription, createddate, createdby) VALUES(?, ?, ?, ?, ?, ?, ?)";
		return insert(sql, newmodel.getTitle(), newmodel.getContent(), newmodel.getCategoryId(), newmodel.getThumbnail(), 
				newmodel.getShortDescription(), newmodel.getCreatedDate(), newmodel.getCreatedBy());
	}
	@Override
	public NewsModel findOne(Long id) {
		String sql = "SELECT * FROM news WHERE id = ?";
		List<NewsModel> news =  query(sql, new NewMapper(), id);
		return news.isEmpty() ? null : news.get(0);
	}

	@Override
	public void update(NewsModel updateNew) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE news SET");
		sql.append(" title = ?,");
		sql.append(" content = ?,");
		sql.append(" thumbnail = ?,");
		sql.append(" shortdescription = ?,");
		sql.append(" categoryid = ?,");
		sql.append(" createddate = ?,");
		sql.append(" createdby = ?,");
		sql.append(" modifieddate = ?,");
		sql.append(" modifiedby = ?");
		sql.append(" WHERE id = ?");
		update(sql.toString(), updateNew.getTitle(), updateNew.getContent(), 
				updateNew.getThumbnail(), updateNew.getShortDescription(), 
				updateNew.getCategoryId(), updateNew.getCreatedDate(), 
				updateNew.getCreatedBy(), updateNew.getModifiedDate(), 
				updateNew.getModifiedBy(), updateNew.getId());

	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM news WHERE id = ?";
		update(sql, id);	
	}

	@Override
	public List<NewsModel> findAll(Pageble pageble) {
//		String sql = "SELECT * FROM news LIMIT ?,?";
		StringBuilder sql = new StringBuilder("SELECT * FROM news");
//		StringUtils.isNoneBlank kiem tra chuoi co trong hay null ko ?
		if(pageble.getSorter() != null && StringUtils.isNoneBlank(pageble.getSorter().getSortName()) && StringUtils.isNoneBlank(pageble.getSorter().getSortBy())) {
			sql.append(" ORDER BY "+pageble.getSorter().getSortName()+" "+pageble.getSorter().getSortBy()+"");
		}
		if(pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT "+pageble.getOffset()+", "+pageble.getLimit()+"");
			
		}
		return query(sql.toString(), new NewMapper());
			
	}



	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM news ";
		return count(sql);
	}
}
