package mc.sn.day14;

public class StudentDTO {
	private int stdNo;
	private String email;
	private int kor;
	private int eng;
	private int math;
	private int sci;
	private int hist;
	private int total;
	private String MgrCode;
	private String accPoint;
	private String localCode;
	
	public int getStdNo() {
		return stdNo;
	}
	public void setStdNo(int stdNo) {
		this.stdNo = stdNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getSci() {
		return sci;
	}
	public void setSci(int sci) {
		this.sci = sci;
	}
	public int getHist() {
		return hist;
	}
	public void setHist(int hist) {
		this.hist = hist;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getMgrCode() {
		return MgrCode;
	}
	public void setMgrCode(String mgrCode) {
		MgrCode = mgrCode;
	}
	public String getAccPoint() {
		return accPoint;
	}
	public void setAccPoint(String accPoint) {
		this.accPoint = accPoint;
	}
	public String getLocalCode() {
		return localCode;
	}
	public void setLocalCode(String localCode) {
		this.localCode = localCode;
	}
	
	
	// 위의 필드에 대해서 get/set을 만드시오
	// 학번, 국,영,수와 지역,담임,성취도 정보를 보여주는 toString 오버라이드 하시오
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.stdNo+" "+this.kor+" "+this.eng+" "+this.math+" "+this.MgrCode+" "+this.accPoint+" "+this.localCode;
	}
}







