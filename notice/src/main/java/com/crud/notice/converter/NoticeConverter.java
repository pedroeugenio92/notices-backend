package com.crud.notice.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.crud.notice.dto.NoticeDTO;
import com.crud.notice.model.Notice;

public class NoticeConverter {
	
	public static Notice fromDTO(NoticeDTO noticeDTO) {
		
		Notice notice = new Notice();
		notice.setId(noticeDTO.getId());
		notice.setTitle(noticeDTO.getTitle());
		notice.setDescription(noticeDTO.getDescription());
		notice.setDate_publish(noticeDTO.getDate_publish());
		notice.setDate_viewed(noticeDTO.getDate_viewed());
		
		return notice;
		
	}
	
	public static NoticeDTO toDTO(Notice notice) {
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setId(notice.getId());
		noticeDTO.setTitle(notice.getTitle());
		noticeDTO.setDescription(notice.getDescription());
		noticeDTO.setDate_publish(notice.getDate_publish());
		noticeDTO.setDate_viewed(notice.getDate_viewed());
		
		return noticeDTO;
	}
	
	public static List<NoticeDTO> toListDTO(List<Notice> notices){
		return notices.stream().map(notice -> {
            return toDTO(notice);
        }).collect(Collectors.toList());
	}

}
