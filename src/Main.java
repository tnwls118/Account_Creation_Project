import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int order = 1;
        ArrayList<Member> members = new ArrayList<>();

        // 프로그램 시작
        System.out.println("명령어 안내\n1.member join\n2.system.out\n3.memeber list\n4.member delete\n5.member detail\n6.member correction\n7.login\n8.logout");

        while (true) {
            System.out.println("\n명령어를 입력하세요:");
            String cmd = sc.nextLine();

            // member 셍성 기능
            if (cmd.equals("member join")) {
                boolean pwsame = false;
                while (!pwsame) {
                    System.out.println("ID:");
                    String id = sc.nextLine();
                    System.out.println("PW:");
                    String pw = sc.nextLine();
                    System.out.println("REPW:");
                    String repw = sc.nextLine();

                    if (pw.equals(repw)) {
                        pwsame = true;
                        Member member = new Member(order, id, pw, Time.time());
                        members.add(member);
                        System.out.println(order + "계정이 생성되었습니다.");
                        order++;
                        break;
                    } else {
                        System.out.println("패스워드가 일치 하지 않습니다.\n패스워드를 다시 확인해주세요.");
                        continue;
                    }
                }
            }
            //member 조회 기능
            else if (cmd.equals("member list")) {
                for (int i = 0; i < members.size(); i++) {
                    System.out.println(members.get(i) + "\n");
                }
                if (members.size() == 0) {
                    System.out.println("조회 가능한 계정이 없습니다.");
                }
            }
            //member delete 기능
            else if (cmd.equals("member delete")) {
                System.out.println("삭제하고싶은 게시물 번호를 입력해주세요:");
                int serach = sc.nextInt();
                sc.nextLine();
                boolean delete = false;
                for (int i = 0; i < members.size(); i++) {
                    if (members.get(i).getnum() == serach) {
                        delete = true;
                        members.remove(i);
                        System.out.println(serach + "번 계정을 삭제하였습니다.");
                        break;
                    }
                }
                if (!delete) {
                    System.out.println("삭제 가능한 계정이 없습니다.");
                }
                //member detail 기능
            } else if (cmd.equals("member detail")) {
                System.out.println("조회 계정 번호:");
                int search = sc.nextInt();
                sc.nextLine();
                boolean detail = false;
                for (int i = 0; i < members.size(); i++) {
                    if (members.get(i).getnum() == search) {
                        detail = true;
                        members.get(i).increaseViewCnt();
                        System.out.println(members.get(i) + "\n조회수:" + members.get(i).getViewCount());
                        break;
                    }
                }
                if (!detail) {
                    System.out.println("조회 가능한 계정이 없습니다.");
                }
            }
            //member 수정 기능
            else if (cmd.equals("member correction")) {
                System.out.println("수정 게시물 번호:");
                int num = sc.nextInt();
                sc.nextLine();
                boolean correctioned = false;
                for (int i = 0; i < members.size(); i++) {
                    if (members.get(i).getnum() == num) {
                        System.out.println("ID:");
                        String ID = sc.nextLine();
                        System.out.println("PW:");
                        String PW = sc.nextLine();
                        System.out.println("REPW:");
                        String REPW = sc.nextLine();

                        Member member1 = new Member(num, ID, PW, Time.time());
                        members.set(i, member1);
                        System.out.println(num + "계정 정보가 수정되었습니다.");
                        correctioned = true;
                        break;
                    } else if (!correctioned) {
                        System.out.println("패스워드를 재확인해주세요");
                    }
                }

            }
            //sytem 종료 기능
            else if (cmd.equals("system.out")) {
                break;
            }
            //로그인 및 로그인 여부 확인 기능
            else if (cmd.equals("login")) {

                System.out.println("ID:");
                String ID = sc.nextLine();
                System.out.println("PW:");
                String PW = sc.nextLine();
                boolean loginSuccess = false;
                for (int i = 0; i < members.size(); i++) {
                    Member member = members.get(i);
                    if ((members.get(i).getId().equals(ID)) && (members.get(i).getPw().equals(PW))) {
                        if (member.isLoggedIn()) {
                            System.out.println("이미 로그인되어 있는 계정입니다.");
                        } else {
                            member.setLoggedIn(true);
                            System.out.println("로그인에 성공하였습니다.");
                        }
                        loginSuccess = true;
                        break;
                    }
                }
                if (!loginSuccess) {
                    System.out.println("ID, PW 재확인이 필요합니다.");
                }
            }
            //logout 기능 구현
            else if (cmd.equals("logout")) {
                System.out.println("logout 계정 ID:");
                String ID = sc.nextLine();
                boolean logoutSuccess = false;
                for (Member member : members) {
                    if (member.getId().equals(ID)) {
                        member.setLoggedIn(false);
                        logoutSuccess = true;
                        System.out.println("계정을 로그아웃 하였습니다.");
                        break;
                    }

                }
                if (!logoutSuccess) {
                    System.out.println("로그아웃에 실패: ID를 재확인 해주세요.");
                }

            } else {
                System.out.println("명령어를 다시 확인해주세요.");
            }

        }
    }
}

class Member {
    private int num;
    private String id;
    private String pw;
    private String repw;
    private String time;
    private boolean loggedIn;
    private int viewCount;


    public Member(int num, String id, String pw, String time) {
        this.num = num;
        this.id = id;
        this.pw = pw;
        this.repw = repw;
        this.time = time;
        this.loggedIn = false;
        viewCount = 0;
    }

    public String toString() {
        return "NUM:" + num + "\nID:" + id + "\nPW:" + pw + "\nTime:" + time;
    }


    public int getnum() {
        return num;
    }

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }


    public void increaseViewCnt() {
        viewCount++;
    }

    public int getViewCount() {
        return viewCount;
    }
}
