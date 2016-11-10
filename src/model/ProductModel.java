package model;



import javafx.print.Collation;
import model.entity.app.Category;
import model.entity.app.product.rentable.RentalProductEntity;
import model.entity.app.product.rentable.SearchedProduct;
import model.entity.app.product.rentable.iface.MyRentalProduct;
import model.entity.app.product.rentable.iface.MyRentedProduct;
import org.hibernate.*;

import model.entity.app.product.rentable.iface.RentalProduct;


import java.text.Collator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by omar on 8/8/16.
 */
public class ProductModel extends BaseModel {
    public void insert(RentalProduct rentalProduct) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(rentalProduct);
        session.getTransaction().commit();
        session.close();
    }

    public void update(RentalProduct rentalProduct) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.update(rentalProduct);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(RentalProduct rentalProduct){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.delete(rentalProduct);
        session.getTransaction().commit();
        session.close();
    }

    public RentalProductEntity getEntityById(int id) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        try {
            return session.get(RentalProductEntity.class, id);
        } finally {
            session.close();
        }
    }

    public RentalProduct getById(int id) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            return session.get(RentalProductEntity.class, id);
        } finally {
            session.close();
        }
    }

    public List<RentalProductEntity> getRentalProduct() {
        Session session = null;
        List rentalProducts = null;
        try {
            session = this.sessionFactory.openSession();
            rentalProducts = session.createQuery("from RentalProductEntity R ORDER BY R.id DESC").list();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }

        return rentalProducts;
    }

    public List<RentalProduct> getRentalProduct(int limit, int offset) {
        if (limit > 15) {
            limit = 15;
        }
        if(limit<=0){
                return new ArrayList<>();
        }
        String hql = "FROM RentalProductEntity p where p.active = true and p.reviewStatus = true ORDER BY p.id DESC";
        Session session = this.sessionFactory.openSession();
        try {
            return session.createQuery(hql)
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit).list();
        } finally {
            session.close();
        }
    }
    public List<RentalProduct> getRentalProductOrderByRating(int limit, int offset) {
        if (limit > 15) {
            limit = 15;
        }
        if(limit<=0){
            return new ArrayList<>();
        }
        String hql = "FROM RentalProductEntity p where p.active = true and p.reviewStatus = true ORDER BY p.averageRating DESC";
        Session session = this.sessionFactory.openSession();
        try {
            return session.createQuery(hql)
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit).list();
        } finally {
            session.close();
        }
    }
    public List<RentalProduct> getOnlyRatedRentalProductOrderByRating(int limit, int offset) {
        if (limit > 15) {
            limit = 15;
        }
        if(limit<=0){
            return new ArrayList<>();
        }
        String hql = "FROM RentalProductEntity p " +
                " where p.active = true " +
                " and p.reviewStatus = true " +
                " and p.averageRating > 0" +
                " ORDER BY p.averageRating DESC";
        Session session = this.sessionFactory.openSession();
        try {
            return session.createQuery(hql)
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit).list();
        } finally {
            session.close();
        }
    }
    public RentalProduct getRentalProductRandom() {

        String hql = "FROM RentalProductEntity p where p.active = true and p.reviewStatus = true ORDER BY RAND()";
        Session session = this.sessionFactory.openSession();
        try {
            return  (RentalProduct) session.createQuery(hql)
                    .setMaxResults(1).uniqueResult();
        } finally {
            session.close();
        }
    }

    public List<RentalProduct> getRentalProductAscending(int limit, int offset) {
        if (limit > 15) {
            limit = 15;
        }
        if(limit<=0){
            return new ArrayList<>();
        }
        String hql = "FROM RentalProductEntity p where p.active = true and p.reviewStatus = true";
        Session session = this.sessionFactory.openSession();
        try {
            return session.createQuery(hql)
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit).list();
        } finally {
            session.close();
        }
    }

    public List<RentalProduct> getRentalProduct(int limit, int offset, int productId) {
        if (limit > 15) {
            limit = 15;
        }
        if(limit<=0){
            return new ArrayList<>();
        }
        String hql = "FROM RentalProductEntity p WHERE p.active = true and p.reviewStatus = true and p.id !=:productId  ORDER BY p.id DESC";
        Session session = this.sessionFactory.openSession();
        try {
            return session.createQuery(hql)
                    .setParameter("productId", productId)
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit).list();
        } finally {
            session.close();
        }
    }

    public List<SearchedProduct> getSearchedProduct(int limit, int offset) {
        if (limit > 15) {
            limit = 15;
        }
        if(limit<=0){
            return new ArrayList<>();
        }
        Session session = this.sessionFactory.openSession();
        try {
            String hql = "FROM RentalProductEntity p WHERE p.active = true and p.reviewStatus = true ORDER BY p.id DESC";
            Query query = session.createQuery(hql);
            query.setFirstResult(offset * limit);
            query.setMaxResults(limit);
            return (List<SearchedProduct>) query.list();
        } finally {
            session.close();
        }
    }

    public RentalProduct getProductSearchById(int id) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            return session.get(RentalProductEntity.class, id);
        } finally {
            session.close();
        }
    }

    public RentalProduct getMyRentalProductById(int id, int ownerId) {
        Session session = this.sessionFactory.openSession();
        String hql = "FROM RentalProductEntity p WHERE p.active = true and p.reviewStatus = true and p.id =:id and p.owner.id = :ownerId";
        try {
            return (RentalProduct) session.createQuery(hql)
                    .setParameter("id", id)
                    .setParameter("ownerId", ownerId)
                    .uniqueResult();
        } finally {
            session.close();
        }
    }

    public List<RentalProduct> getMyRentalProductList(int ownerId, int limit, int offset) {
        Session session = this.sessionFactory.openSession();
        String hql = "FROM RentalProductEntity myRentalProduct LEFT JOIN FETCH myRentalProduct.rentInf where myRentalProduct.owner.id = :ownerId ORDER BY myRentalProduct.id DESC  ";
        try {
            return session.createQuery(hql)
                    .setParameter("ownerId", ownerId)
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit).list();
        } finally {
            session.close();
        }
    }
    public List<RentalProduct> getMyRentalProductList(int ownerId) {
        Session session = this.sessionFactory.openSession();
        String hql = "FROM RentalProductEntity myRentalProduct LEFT JOIN FETCH myRentalProduct.rentInf where myRentalProduct.owner.id = :ownerId ORDER BY myRentalProduct.id DESC  ";
        try {
            return session.createQuery(hql)
                    .setParameter("ownerId", ownerId)
                    .list();
        } finally {
            session.close();
        }
    }

    public boolean approveRentalProduct(int productId) {
        Session session = null;
        boolean flag = true;
        Transaction transaction = null;
        try {

            session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            RentalProductEntity rentalProductEntity = session.get(RentalProductEntity.class, productId);
            rentalProductEntity.setReviewStatus(true);
            session.update(rentalProductEntity);
            transaction.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            flag = false;
            if (transaction != null)
                transaction.rollback();
        } finally {


            if (session != null)
                session.close();

        }

        return flag;

    }

    public boolean disapproveRentalProduct(int productId) {
        Session session = null;
        boolean flag = true;
        Transaction transaction = null;
        try {
            session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            RentalProductEntity rentalProductEntity = session.get(RentalProductEntity.class, productId);
            rentalProductEntity.setReviewStatus(false);
            session.update(rentalProductEntity);
            transaction.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            flag = false;
            if (transaction != null)
                transaction.rollback();
        } finally {


            if (session != null)
                session.close();
        }

        return flag;

    }




    public List<RentalProduct> getRentalProductByCategoryId(int categoryId, int limit, int offset){

        Session session = this.sessionFactory.openSession();
        String hql = "FROM Category " +
                    " WHERE id =:id" ;
        try{
            Category category = (Category) session.createQuery(hql)
                    .setParameter("id", categoryId)
                    .setMaxResults(1)
                    .uniqueResult();
            if(category==null){
                return new ArrayList<>();
            }
            return this.getRentalProductByCategory(category,limit,offset);

        } finally {

            session.close();
        }
    }
    public List<RentalProduct> getRentalProductByCategory(Category category,int limit, int offset){
        List<Category> subCategory =  category.getSubcategory();
        List<Integer> categoryIdList = new ArrayList<>();
        categoryIdList.add(category.getId());

        if(subCategory!=null){
            categoryIdList.addAll(subCategory.stream().map(Category::getId).collect(Collectors.toList()));
        }

        System.out.println(categoryIdList);
        Session session = this.sessionFactory.openSession();
        String hql = "FROM RentalProductEntity rentalProduct " +
                    " LEFT JOIN FETCH rentalProduct.productCategories productCategory" +
                    " WHERE rentalProduct.reviewStatus = true  " +
                    " and productCategory.category.id IN ( :categoryIdList ) " +
                    " order by rentalProduct.id desc ";
        try{
            return session.createQuery(hql)
                    .setParameter("categoryIdList", categoryIdList)
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit)
                    .list();
        } finally {

            session.close();
        }
    }
    public List<RentalProduct> getRentalProductByTitle(String title, int limit, int offset){
        Session session = this.sessionFactory.openSession();
        String hql = "FROM RentalProductEntity rentalProduct " +
                "  WHERE  rentalProduct.reviewStatus = true" +
                " AND rentalProduct.name LIKE :title" +
                " order by rentalProduct.id desc ";
        try{
            return session.createQuery(hql)
                    .setParameter("title", "%" +title+ "%")
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit)
                    .list();
        } finally {

            session.close();
        }
    }
    public List<RentalProduct> getRentalProductByCategoryIdTitle(int categoryId, String title, int limit, int offset){
        if(title == null || title.isEmpty()){
            return this.getRentalProductByCategoryId(categoryId, limit, offset);
        }
        Session session = this.sessionFactory.openSession();
        String hql = "FROM RentalProductEntity rentalProduct " +
                " LEFT JOIN FETCH rentalProduct.productCategories productCategory " +
                "  WHERE productCategory.category.id=:categoryId"+
                "  and rentalProduct.reviewStatus = true" +
                " AND rentalProduct.name LIKE :title" +
                " order by rentalProduct.id desc ";
        try{
            return session.createQuery(hql)
                    .setParameter("categoryId", categoryId)
                    .setParameter("title", "%" +title+ "%")
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit)
                    .list();
        } finally {

            session.close();
        }
    }
    public List<MyRentedProduct> getMyCurrentRentedProduct(int renteeId, int limit, int offset) {
        Session session = this.sessionFactory.openSession();
        String hql = "FROM RentalProductEntity myRentedProduct LEFT JOIN  FETCH myRentedProduct.rentInf reninf " +
                " where reninf.rentee.id=:renteeId " +
                " and reninf.expired = false ORDER BY myRentedProduct.id DESC  ";
        try {
            return session.createQuery(hql)
                    .setParameter("renteeId", renteeId)
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit).list();
        } finally {

            session.close();
        }
    }
    public List<MyRentedProduct> getMyCurrentRentedProduct(int renteeId) {

        Session session = this.sessionFactory.openSession();
        String hql = "FROM RentalProductEntity myRentedProduct JOIN FETCH myRentedProduct.rentInf reninf " +
                " where reninf.rentee.id=:renteeId " +
                " and reninf.expired = false ORDER BY myRentedProduct.id DESC  ";
        try {
            return session.createQuery(hql)
                    .setParameter("renteeId", renteeId)
                    .list();
        } finally {

            session.close();
        }
    }

    /* My product on rent */

    public List<MyRentalProduct> getMyCurrentProductOnRent(int renteeId, int limit, int offset) {
        if(limit<=0){
            return new ArrayList<>();
        }

        Session session = this.sessionFactory.openSession();
        String hql = "select DISTINCT myRentedProduct FROM RentalProductEntity myRentedProduct JOIN  FETCH myRentedProduct.rentInf reninf " +
                " where myRentedProduct.owner.id=:renteeId " +
                " and reninf.expired = false ORDER BY myRentedProduct.id DESC  ";
        try {
            return session.createQuery(hql)
                    .setParameter("renteeId", renteeId)
                    .setFirstResult(offset * limit)
                    .setMaxResults(limit).list();
        } finally {

            session.close();
        }
    }
    public List<MyRentalProduct> getMyCurrentProductOnRent(int renteeId) {
        Session session = this.sessionFactory.openSession();
        String hql = "select DISTINCT(myRentedProduct) FROM RentalProductEntity myRentedProduct JOIN  FETCH myRentedProduct.rentInf reninf " +
                " where myRentedProduct.owner.id=:renteeId " +
                " and reninf.expired = false ORDER BY myRentedProduct.id DESC  ";
        try {
            return session.createQuery(hql)
                    .setParameter("renteeId", renteeId)
                    .setFirstResult(0)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .list();
        } finally {

            session.close();
        }
    }
    public List<RentalProduct> getRentalProductByDistance(String title,double centerLatitude,double centerLongitude,float radius,int limit,int offset){
        List<Integer> rentalProductIds =  this.getRentalProductIdByDistance(title, centerLatitude, centerLongitude, radius, limit, offset);


        Session session = this.sessionFactory.openSession();
        System.out.println(centerLatitude+" "+centerLongitude+" "+radius+" "+limit+" "+offset+" TITLE : PRODUCT ID "+title+" "+rentalProductIds);
        if(rentalProductIds==null || rentalProductIds.size()==0){
            return new ArrayList<>();
        }
        try{

            return session.createQuery("FROM RentalProductEntity rpe  " +
                    " where   rpe.id in (:rentalProductIds) ")
                    .setParameterList("rentalProductIds", rentalProductIds)
                    .list();

        }finally {
            session.close();
        }

    }
    public List<RentalProduct> getRentalProductByDistance(Integer categoryId,double centerLatitude,double centerLongitude,float radius,int limit,int offset){

        List<Integer> rentalProductIds =  this.getRentalProductIdByDistance(categoryId,centerLatitude, centerLongitude, radius, limit, offset);
        Session session = this.sessionFactory.openSession();
        System.out.println(centerLatitude+" "+centerLongitude+" "+radius+" "+limit+" "+offset+"  CAT: "+rentalProductIds);
        if(rentalProductIds==null || rentalProductIds.size()==0){
            return new ArrayList<>();
        }
        try{
            return session.createQuery("FROM RentalProductEntity rpe JOIN fetch rpe.productCategories productCategories " +
                    " where rpe.id in (:rentalProductIds) ")
                    .setParameterList("rentalProductIds", rentalProductIds)
                    .list();
        }finally {
            session.close();
        }

    }
    public List<RentalProduct> getRentalProductByDistance(Integer categoryId,String title,double centerLatitude,double centerLongitude,float radius,int limit,int offset){

        List<Integer> rentalProductIds =  this.getRentalProductIdByDistance(categoryId, title, centerLatitude, centerLongitude, radius, limit, offset);
        Session session = this.sessionFactory.openSession();
        System.out.println(centerLatitude+" "+centerLongitude+" "+radius+" "+limit+" "+offset+" TITLE AND CAT: "+title+rentalProductIds);
        if(rentalProductIds==null || rentalProductIds.size()==0){
            return new ArrayList<>();
        }
        try{
            return session.createQuery("FROM RentalProductEntity " +
                    " where id in (:rentalProductIds) ")
                    .setParameterList("rentalProductIds", rentalProductIds)
                    .list();
        }finally {
            session.close();
        }

    }
    public List<RentalProduct> getRentalProductByDistance(double centerLatitude,double centerLongitude,float radius,int limit,int offset){

        List<Integer> locationIds =  this.getProductLocationByDistance(centerLatitude, centerLongitude, radius, limit, offset);

        Session session = this.sessionFactory.openSession();
        System.out.println("locationIds "+locationIds);
        if(locationIds==null || locationIds.size()==0){
            return new ArrayList<>();
        }
        try{
            return session.createQuery("FROM RentalProductEntity " +
                    " where productLocation.id in (:productLocationIds) " +
                    " AND reviewStatus = true ")
                    .setParameterList("productLocationIds", locationIds)
                    .list();
        }finally {
            session.close();
        }

    }

/*

    Here's the SQL statement that will find the closest 20 locations that are within a radius of 25 miles to the 37, -122 coordinate.
    It calculates the distance based on the latitude/longitude of that row
    and the target latitude/longitude, and then asks for only rows where the distance value is less than 25, orders the whole query by distance,
    and limits it to 20 results. To search by kilometers instead of miles, replace 3959 with 6371.
 */
//    Hibernate search is not working thats'y below function is written
    private   List<Integer> getProductLocationByDistance(double centerLatitude, double centerLongitude, float radius, int limit, int offset){
        Session session = this.sessionFactory.openSession();
        try{
            List<Integer> ids = session.createSQLQuery("SELECT  id " +
                    "FROM product_location " +
                    "where ( (6371 * acos(cos(radians(:centerLatitude)) * cos(radians(lat)) * cos( radians(lng) - radians(:centerLongitude)) + sin(radians(:centerLatitude)) * " +
                    "sin(radians(lat))))  <=:radius ) " +
                    " LIMIT :offset , :limit ")
                    .setParameter("centerLatitude", centerLatitude)
                    .setParameter("centerLongitude",centerLongitude)
                    .setParameter("radius", radius)
                    .setParameter("offset",offset*limit)
                    .setParameter("limit",limit)
                    .list();
            return ids;
        }finally {
            session.close();
        }
    }
    private   List<Integer> getRentalProductIdByDistance(String title,double centerLatitude, double centerLongitude, float radius, int limit, int offset){
        Session session = this.sessionFactory.openSession();
        try{
            List<Integer> ids = session.createSQLQuery("SELECT  product.id " +
                    "FROM product_location " +
                    " JOIN product on  product_location.product_id = product.id " +
                    " where ( (6371 * acos(cos(radians(:centerLatitude)) * cos(radians(lat)) * cos( radians(lng) - radians(:centerLongitude)) + sin(radians(:centerLatitude)) * " +
                    " sin(radians(lat))))  <=:radius ) " +
                    " and product.review_status = 1 and product.name like :title "+
                    " LIMIT :offset , :limit ")
                    .setParameter("title", "%" + title+"%")
                    .setParameter("centerLatitude", centerLatitude)
                    .setParameter("centerLongitude",centerLongitude)
                    .setParameter("radius", radius)
                    .setParameter("offset",offset*limit)
                    .setParameter("limit",limit)
                    .list();
            return ids;
        }finally {
            session.close();
        }
    }
    private   List<Integer> getRentalProductIdByDistance(int categoryId,String title,double centerLatitude, double centerLongitude, float radius, int limit, int offset){
        Session session = this.sessionFactory.openSession();
        try{
            List<Integer> ids = session.createSQLQuery("SELECT  product.id " +
                    "FROM product_location " +
                    " JOIN product on  product_location.product_id = product.id " +
                    " JOIN product_category on product.id = product_category.product_id " +
                    " where ( (6371 * acos(cos(radians(:centerLatitude)) * cos(radians(lat)) * cos( radians(lng) - radians(:centerLongitude)) + sin(radians(:centerLatitude)) * " +
                    " sin(radians(lat))))  <=:radius ) " +
                    " and product.review_status = 1 " +
                    " and product_category.category_id = :categoryId" +
                    " and product.name like :title " +
                    " LIMIT :offset , :limit ")
                    .setParameter("title", "%"+title+"%")
                    .setParameter("centerLatitude", centerLatitude)
                    .setParameter("centerLongitude",centerLongitude)
                    .setParameter("categoryId",categoryId)
                    .setParameter("radius", radius)
                    .setParameter("offset",offset*limit)
                    .setParameter("limit",limit)
                    .list();
            return ids;
        }finally {
            session.close();
        }
    }
    private   List<Integer> getRentalProductIdByDistance(int categoryId,double centerLatitude, double centerLongitude, float radius, int limit, int offset){
        Session session = this.sessionFactory.openSession();
        try{
            List<Integer> ids = session.createSQLQuery("SELECT  product.id " +
                    "FROM product_location " +
                    " JOIN product on  product_location.product_id = product.id " +
                    " JOIN product_category on product.id = product_category.product_id " +
                    " where ( (6371 * acos(cos(radians(:centerLatitude)) * cos(radians(lat)) * cos( radians(lng) - radians(:centerLongitude)) + sin(radians(:centerLatitude)) * " +
                    " sin(radians(lat))))  <=:radius ) " +
                    " and product.review_status = 1 " +
                    " and product_category.category_id=:categoryId"+
                    " LIMIT :offset , :limit ")
                    .setParameter("centerLatitude", centerLatitude)
                    .setParameter("centerLongitude",centerLongitude)
                    .setParameter("categoryId",categoryId)
                    .setParameter("radius", radius)
                    .setParameter("offset",offset*limit)
                    .setParameter("limit",limit)
                    .list();
            return ids;
        }finally {
            session.close();
        }
    }
    public  List<RentalProduct> getRentalProductForSearch(Integer categoryId,String title,Double centerLatitude,Double centerLongitude,Float radius,int limit,int offset){

        boolean haveCategory = (categoryId!=null && categoryId>0);
        boolean haveTitle = (title!=null && !title.equals(""));

        if(radius!=null){

            if(haveCategory && haveTitle ){
                return this.getRentalProductByDistance(categoryId, title, centerLatitude, centerLongitude, radius, limit, offset);
            }else if(haveCategory){
                return this.getRentalProductByDistance(categoryId,centerLatitude, centerLongitude, radius, limit, offset);
            }else if(haveTitle) {
                return this.getRentalProductByDistance(title, centerLatitude, centerLongitude, radius, limit, offset);
            }else{
                return this.getRentalProductByDistance(centerLatitude, centerLongitude, radius, limit, offset);
            }

        }

        if(haveCategory && haveTitle ){
            return this.getRentalProductByCategoryIdTitle(categoryId, title,limit, offset);
        }
        if(haveCategory){
            return this.getRentalProductByCategoryId(categoryId,limit, offset);
        }
        if(haveTitle){
            return this.getRentalProductByTitle(title, limit, offset);
        }
        return new ArrayList<>();
    }


    //    Hibernate search spatial is not working


//    private List<ProductLocation> getProductLocationByDistance(double centerLatitude,double centerLongitude,float radius,int offset,int limit){
//        Session session = this.sessionFactory.openSession();
//        FullTextSession fullTextSession =  Search.getFullTextSession(session);
//        System.out.println(centerLatitude+" "+centerLongitude+" "+radius+" "+offset+" "+limit);
//        QueryBuilder builder = fullTextSession.getSearchFactory()
//                .buildQueryBuilder().forEntity(ProductLocation.class).get();
//        org.apache.lucene.search.Query luceneQuery = builder.spatial().onField("home")
//                .within(radius, Unit.KM)
//                .ofLatitude(centerLatitude)
//                .andLongitude(centerLongitude).createQuery();
//        System.out.println(luceneQuery.toString());
//        org.hibernate.Query hibQuery = fullTextSession
//                .createFullTextQuery(luceneQuery, ProductLocation.class);
//        List<ProductLocation> results = hibQuery.list();
//        System.out.println(hibQuery.getQueryString());
//        System.out.println(hibQuery.list().size());
//        return results;//fullTextSession.createFullTextQuery(luceneQuery, ProductLocation.class).list(); //.setFirstResult(offset).setMaxResults(limit)
//    }
}
