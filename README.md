# Servlet & JSP 학습
> 이 저장소는 Servlet & JSP 학습내용을 저장해둔 곳입니다.

## 설치 환경
- 이클립스 웹 디벨로퍼
- [톰캣 10.1](https://tomcat.apache.org/download-10.cgi)
> 프로젝트 우클릭 properties -> java Build Path의 Libraries탭 -> 우측 add Libraries - server Runtime 톰캣 선택 후 Finish
- Window -> Preferences -> 'enco' 검색 후 "CSS, HTML, JSP, XML Files" 항목을 'UTF-8'로 수정

## 배포
- 프로젝트 우클릭 Properties > Web Project Settings > Context root: '수정'
- 프로젝트 우클릭 Export > WAR file > 임시폴더에 저장
- WAR file을 톰캣 설치폴더 > webapps > 에 옮기기
- 톰캣 설치폴더 > bin > startup.bat 실행하면 배포완료
- ※ startup.bat 실행 시 창이 꺼질 경우 이클립스에서 서버(8080포트) 살아있는지 확인하고 죽이기

## JSP 경로
> 워크스페이스 경로\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\work\Catalina\localhost\프로젝트명\org\apache\jsp