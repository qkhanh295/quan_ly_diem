import java.util.ArrayList;
import java.util.List;

public class XuatBangDiem {

    // Tính điểm trung bình có trọng số, làm tròn 1 chữ số thập phân
    static double tinhDiemTB(double[] diem, double[] heSo) {
        double tong = 0, tongHeSo = 0;
        for (int i = 0; i < diem.length; i++) {
            tong += diem[i] * heSo[i];
            tongHeSo += heSo[i];
        }
        double tb = tong / tongHeSo;
        return Math.round(tb * 10) / 10.0;
    }

    // Xếp loại học lực theo điểm trung bình
    static String xepLoai(double tb) {
        if (tb >= 8.0) return "Gioi";
        if (tb >= 6.5) return "Kha";
        if (tb >= 5.0) return "Trung binh";
        return "Yeu";
    }

    static final String[] TEN = {"An", "Binh", "Chi", "Dung"};
    static final double[] TB = {8.6, 7.4, 6.8, 5.2};

    // Xuất bảng điểm: mỗi sinh viên một dòng
    static List<String> xuatBang(String[] ten, double[] tb) {
        List<String> dong = new ArrayList<>();
        for (int i = 0; i < ten.length; i++) {
            dong.add("Hang " + (i + 1) + ": " + ten[i] + " - " + tb[i] + " - " + xepLoai(tb[i]));
        }
        return dong;
    }

    static int kiem(String ten, boolean dat) {
        System.out.println((dat ? "PASS  " : "FAIL  ") + ten);
        return dat ? 0 : 1;
    }

    static int kiemTra(boolean tatCa) {
        int loi = 0;
        if (tatCa) {
            double tb = tinhDiemTB(new double[]{7.6, 8.2, 8.0}, new double[]{0.2, 0.3, 0.5});
            loi += kiem("Lam tron diem TB (kỳ vọng 8.0)", Math.abs(tb - 8.0) < 1e-9);
        }
        String het = String.join("\n", xuatBang(TEN, TB));
        loi += kiem("Bảng điểm có đủ sinh viên", het.contains(TEN[TEN.length - 1]));
        if (tatCa) loi += kiem("Xep loai 8.0 la Gioi", xepLoai(8.0).equals("Gioi"));
        return loi == 0 ? 0 : 1;
    }

    public static void main(String[] args) {
        String che = args.length > 0 ? args[0] : "";
        if (che.equals("--test")) System.exit(kiemTra(true));
        if (che.equals("--test-xuat")) System.exit(kiemTra(false));
        for (String d : xuatBang(TEN, TB)) System.out.println(d);
    }
}
