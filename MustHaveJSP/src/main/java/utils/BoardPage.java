package utils;

public class BoardPage {
	public static String pagingStr(int totalCount, int pageSize, int blockPage, int pageNum, String reqUrl) {
		String pagingStr = "";
		// 전체 페이지 수 계산
		int totalPages = (int) (Math.ceil(((double) totalCount / pageSize)));
		// 이전 페이지 블록 바로가기 출력
		int pageTemp = (((pageNum - 1) / blockPage) * blockPage) + 1;

		if (pageTemp != 1) {
			pagingStr += "<a href='" + reqUrl + "?pageNum=1'>[첫 페이지]</a>";
			pagingStr += "&nbsp;";
			pagingStr += "<a href='" + reqUrl + "?pageNum=" + (pageTemp - 1) + "'>[이전 블록]</a>";
		}
		// 각 페이지 번호 출력
		int blockCount = 1;
		while (blockCount <= blockPage && pageTemp <= totalPages) {
			if (pageTemp == pageNum) {
				pagingStr += "&nbsp;" + pageTemp + "&nbsp;";
			} else {
				pagingStr += "&nbsp;<a href='" + reqUrl + "?pageNum=" + pageTemp + "'>" + pageTemp + "</a>&nbsp;";
			}
			pageTemp++;
			blockCount++;
		}
		// 다음 페이지 블록 바로가기 출력
		if (pageTemp <= totalPages) {
			pagingStr += "<a href='" + reqUrl + "?pageNum=" + pageTemp + "'>[다음 블록]</a>";
			pagingStr += "&nbsp;";
			pagingStr += "<a href='" + reqUrl + "?pageNum=" + totalPages + "'>[마지막 페이지]</a>";
		}

		return pagingStr;

	}

	public static String pagingStr1(int totalCount, int pageSize, int blockPage, int pageNum, String reqUrl) {
		String pagingStr = "";

		// 전체 페이지 수 계산
		int totalPages = (int) Math.ceil((double) totalCount / pageSize);

		// 페이지 목록의 중앙 위치 계산
		int middlePage = (blockPage / 2); // 항상 5개의 페이지 목록을 유지하므로 중앙은 2입니다

		// 시작 페이지와 끝 페이지 계산
		int startPage = Math.max(1, pageNum - middlePage);
		int endPage = Math.min(totalPages, pageNum + middlePage);

		// 페이지 목록이 5개를 유지하도록 조정
		while (endPage - startPage + 1 < blockPage && startPage > 1) {
			startPage--;
		}
		while (endPage - startPage + 1 < blockPage && endPage < totalPages) {
			endPage++;
		}

		// 현재 페이지 주변의 페이지 번호 출력
		for (int i = startPage; i <= endPage; i++) {
			if (i == pageNum) {
				pagingStr += "&nbsp;<strong>" + i + "</strong>&nbsp;";
			} else {
				pagingStr += "&nbsp;<a href='" + reqUrl + "?pageNum=" + i + "'>" + i + "</a>&nbsp;";
			}
		}

		return pagingStr;

	}
}
