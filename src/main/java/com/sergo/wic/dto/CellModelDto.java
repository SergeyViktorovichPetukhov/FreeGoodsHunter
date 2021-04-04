package com.sergo.wic.dto;

import com.sergo.wic.entities.enums.ShareCellType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CellModelDto
//        implements Comparable<CellModelDto>
{
    private String productPhotoUrl;
    private String shareId;
    private String companyId;
    private ShareCellType cellType;
    private String productName;
    private String date;
    private int distanceToNearestItem;
    private double productPrice;
    private int pickedItemsCount;
    private int numItemsToWin;
    private boolean isVerificated;

//    @Override
//    public int compareTo(CellModelDto other) {
//        System.out.println(this.productName + "    this product name , " + other.getProductName() + "     other product name");
//        return Comparator.comparing((cellModel) -> new TypeSorter()).thenComparing()
//    }
//
//    private int compareByPrice(CellModelDto other) {
//        System.out.println(this.productName + " price : " + this.productPrice + " ," + other.productName + " price : " + other.productPrice);
//        return other.getProductPrice() < this.productPrice ? 1 : 0;
//    }
//
//    public class TypeSorter implements Comparator<CellModelDto> {
//        public int compare(CellModelDto o1, CellModelDto o2) {
//            return o1.getCellType().getValue() > (o2.getCellType().getValue()) ? 1 : 0;
//        }
//    }
//
//    public class VerificatedSorter implements Comparator<CellModelDto> {
//        public int compare(CellModelDto o1, CellModelDto o2) {
//            if (o1.isVerificated() & o2.isVerificated()) {
//                return 0;
//            }
//            if (o1.isVerificated() & !o2.isVerificated()) {
//                return 1;
//            }
//            if (!o1.isVerificated() & o2.isVerificated()) {
//                return -1;
//            }
//            else {
//                return 0;
//            }
//        }
//    }
//
//    public class PriceSorter implements Comparator<CellModelDto> {
//        public int compare(CellModelDto o1, CellModelDto o2) {
//            return o1.getProductPrice() > o2.getProductPrice() ? 1 : 0;
//        }
//    }
}




//        Comparator<CellModelDto> comparator = (obj1, obj2) -> {
//            if (obj1.getCellType() == ShareCellType.STARTED) {
//                return 1;
//            } else if (obj1.getCellType() == ShareCellType.CHOSEN) {
//                return 1;
//            } else if (obj1.getCellType() == ShareCellType.ACTIVE & obj1.isVerificated()) {
//                return 1;
//            } else if (obj1.getCellType() == ShareCellType.ACTIVE & !obj1.isVerificated()) {
//                return 1;
//            } else if (obj1.getCellType() == ShareCellType.PREVIEW & obj1.isVerificated()) {
//                return 1;
//            } else if (obj1.getCellType() == ShareCellType.PREVIEW & !obj1.isVerificated()) {
//                return 1;
//            } else if (obj1.getCellType() == ShareCellType.FINISHED & obj1.isVerificated()) {
//                return 1;
//            }
//            return 0;
//        };



//if (this.cellType.getValue() > other.getCellType().getValue()) {
//        System.out.println(this.isVerificated + "   " + this.getCellType().getValue() + "     " + other.isVerificated + "    " + other.getCellType().getValue());
//        return compareByPrice(other) * 3;
//        }
//        if (this.cellType.getValue() < other.getCellType().getValue()) {
//        System.out.println(this.isVerificated + "   " + this.getCellType().getValue() + "     " + other.isVerificated  + "    " + other.getCellType().getValue());
//        return compareByPrice(other);
//        }
//        if (this.cellType.getValue() == other.getCellType().getValue()) {
//        System.out.println(this.isVerificated + "   " + this.getCellType().getValue() + "     " + other.isVerificated + "    "  + other.getCellType().getValue());
//        return compareByPrice(other) * 2 ;
//        }
//        return -1;

//        if ( this.cellType.getValue() < other.getCellType().getValue()) {
//            if (this.isVerificated & other.isVerificated) {
//                System.out.println(compareByPrice(other) + " 1.1");
//                return compareByPrice(other);
//            }
//            if (this.isVerificated & !other.isVerificated) {
//                System.out.println(compareByPrice(other) + " 1.2");
//                return compareByPrice(other);
//            }
//            if (other.isVerificated) {
//                System.out.println(compareByPrice(other) + " 1.3");
//                return compareByPrice(other);
//            }
//        }
//        if ( this.cellType.getValue() > other.getCellType().getValue()) {
//            if (this.isVerificated & other.isVerificated) {
//                System.out.println(compareByPrice(other) + " 2.1");
//                return compareByPrice(other);
//            }
//            if (this.isVerificated & !other.isVerificated) {
//                System.out.println(compareByPrice(other) + " 2.2");
//                return compareByPrice(other);
//            }
//            if (other.isVerificated) {
//                System.out.println(compareByPrice(other) + " 2.3");
//                return compareByPrice(other);
//            }
//        }
//        if ( this.cellType.getValue() == other.getCellType().getValue()) {
//            if (this.isVerificated & other.isVerificated) {
//                System.out.println(compareByPrice(other) + " 3.1");
//                return compareByPrice(other);
//            }
//            if (this.isVerificated & !other.isVerificated) {
//                System.out.println(compareByPrice(other) + " 3.2");
//                return compareByPrice(other);
//            }
//            if (other.isVerificated) {
//                System.out.println(compareByPrice(other) + " 3.3");
//                return compareByPrice(other);
//            }
//        }
