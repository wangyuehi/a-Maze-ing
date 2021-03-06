p a c k a g e   n e w M a z e ;  
 i m p o r t   j a v a . a w t . B o r d e r L a y o u t ;  
 i m p o r t   j a v a . a w t . D i m e n s i o n ;  
 i m p o r t   j a v a . a w t . G r a p h i c s ;  
 i m p o r t   j a v a . a w t . G r i d L a y o u t ;  
 i m p o r t   j a v a . a w t . T e x t F i e l d ;  
 i m p o r t   j a v a . a w t . T o o l k i t ;  
 i m p o r t   j a v a . a w t . e v e n t . * ;  
 i m p o r t   j a v a . n e t . U R L ;  
  
 i m p o r t   j a v a x . s w i n g . * ;  
  
  
  
  
  
 / * * * * * * * * * * * * * * * * * * * * * *  
   *    
   *   @ a u t h o r   w a n g y u e  
   *  
   * * * * * * * * * * * * * * * * * * * * * * /  
  
 p u b l i c   c l a s s   M a z e H o m e   {  
 	 J P a n e l   p a n e l ; / /   � �   � � � �  
 	 J B u t t o n   s h o w M a z e ;  
 	 J B u t t o n   e x i t G a m e ;  
 	 J P a n e l   t o o l P a d ;  
 	 J F r a m e   h o m e ;  
 	 / /   � � � � � �  
 	 p u b l i c   s t a t i c   f i n a l   i n t   G A M E _ W I D T H   =   8 0 0 ;  
 	 p u b l i c   s t a t i c   f i n a l   i n t   G A M E _ H E I G H T   =   6 0 0 ;  
 	 p r i v a t e   J L a b e l   i n t r o 1 ;  
 	 p r i v a t e   J L a b e l   i n t r o ;  
 	 p r i v a t e   J P a n e l   i n t r o d u c t i o n ;  
  
 	 p u b l i c   M a z e H o m e ( )   {  
  
 	 	 h o m e   =   n e w   J F r a m e ( " m a i n " ) ;    
 	 	 p a n e l   =   n e w   J P a n e l ( )   ;  
 	 	 t o o l P a d   =   n e w   J P a n e l ( ) ;  
 	 	 p a n e l . s e t L a y o u t ( n e w   B o r d e r L a y o u t ( ) ) ;  
 	 	 t o o l P a d . s e t L a y o u t ( n u l l ) ;  
 	 	 s h o w M a z e = n e w   J B u t t o n ( " S h o w   M a z e " ) ;  
 	 	 e x i t G a m e = n e w   J B u t t o n ( " E x i t   M a z e " ) ;  
  
 	 	 p a n e l . a d d ( t o o l P a d , B o r d e r L a y o u t . W E S T ) ;  
 	 	 t o o l P a d . s e t P r e f e r r e d S i z e ( n e w   j a v a . a w t . D i m e n s i o n ( 2 2 0 ,   6 5 7 ) ) ;  
 	 	 t o o l P a d . s e t B a c k g r o u n d ( n e w   j a v a . a w t . C o l o r ( 2 5 5 , 2 3 1 , 2 0 6 ) ) ;  
 	 	 {  
 	 	 	 i n t r o d u c t i o n   =   n e w   J P a n e l ( ) ;  
 	 	 	 p a n e l . a d d ( i n t r o d u c t i o n ,   B o r d e r L a y o u t . E A S T ) ;  
 	 	 	 i n t r o d u c t i o n . s e t P r e f e r r e d S i z e ( n e w   j a v a . a w t . D i m e n s i o n ( 5 8 8 ,   4 7 7 ) ) ;  
 	 	 	 i n t r o d u c t i o n . s e t O p a q u e ( f a l s e ) ;  
 	 	 	 i n t r o d u c t i o n . s e t L a y o u t ( n u l l ) ;  
 	 	 	 {  
 	 	 	 	 i n t r o   =   n e w   J L a b e l ( ) ;  
 	 	 	 	 i n t r o d u c t i o n . a d d ( i n t r o ) ;  
 	 	 	 	 i n t r o . s e t I c o n ( n e w   I m a g e I c o n ( g e t C l a s s ( ) . g e t C l a s s L o a d e r ( ) . g e t R e s o u r c e ( " n e w M a z e / 6 2 2 1 _ 1 1 8 0 3 7 5 5 . G I F " ) ) ) ;  
 	 	 	 	 i n t r o . s e t B o u n d s ( 3 9 0 ,   2 9 1 ,   1 7 3 ,   1 7 4 ) ;  
 	 	 	 }  
 	 	 	 {  
 	 	 	 	 i n t r o 1   =   n e w   J L a b e l ( ) ;  
 	 	 	 	 i n t r o d u c t i o n . a d d ( i n t r o 1 ) ;  
 	 	 	 	 i n t r o 1 . s e t B o u n d s ( 9 ,   5 8 ,   3 9 2 ,   3 0 4 ) ;  
 	 	 	 	 i n t r o 1 . s e t I c o n ( n e w   I m a g e I c o n ( g e t C l a s s ( ) . g e t C l a s s L o a d e r ( ) . g e t R e s o u r c e ( " n e w M a z e / u t s i n g . g i f " ) ) ) ;  
 	 	 	 }  
 	 	 }  
 	 	 t o o l P a d . a d d ( s h o w M a z e ) ;  
 	 	 s h o w M a z e . s e t B o u n d s ( 1 2 ,   1 9 1 ,   1 9 1 ,   3 2 ) ;  
 	 	  
 	 	 s h o w M a z e . s e t B a c k g r o u n d ( n e w   j a v a . a w t . C o l o r ( 2 5 5 , 2 1 0 , 2 3 3 ) ) ;  
 	 	 s h o w M a z e . s e t B o r d e r P a i n t e d ( f a l s e ) ;  
 	 	 t o o l P a d . a d d ( e x i t G a m e ) ;  
 	 	 e x i t G a m e . s e t B o u n d s ( 1 2 ,   2 5 7 ,   1 9 1 ,   3 2 ) ;  
 	 	 e x i t G a m e . s e t B a c k g r o u n d ( n e w   j a v a . a w t . C o l o r ( 2 5 5 , 2 1 0 , 2 3 3 ) ) ;  
 	 	 e x i t G a m e . s e t B o r d e r P a i n t e d ( f a l s e ) ;  
  
 	 	 h o m e . a d d ( p a n e l ) ;  
 	 	 I c o n   i c o n = n e w   I m a g e I c o n ( " i n t r o . p n g " ) ;  
 	 	  
 	 	 s h o w M a z e . a d d A c t i o n L i s t e n e r ( n e w   S h o w M o u s e L i s t e n e r ( ) ) ;  
 	 	 e x i t G a m e . a d d A c t i o n L i s t e n e r ( n e w   E x i t M o u s e L i s t e n e r ( ) ) ;  
  
 	 	 p a n e l . s e t B a c k g r o u n d ( n e w   j a v a . a w t . C o l o r ( 2 5 5 , 2 5 5 , 2 2 3 ) ) ;  
 	 	 p a n e l . s e t P r e f e r r e d S i z e ( n e w   j a v a . a w t . D i m e n s i o n ( 9 9 9 ,   5 5 5 ) ) ;  
 	 	 h o m e . s e t S i z e ( 8 2 6 ,   5 1 5 ) ;  
 	 	 h o m e . s e t V i s i b l e ( t r u e ) ;  
 	 	 h o m e . s e t P r e f e r r e d S i z e ( n e w   j a v a . a w t . D i m e n s i o n ( 8 2 6 ,   5 1 5 ) ) ;  
  
 	 }  
 	 p u b l i c   s t a t i c   v o i d   m a i n ( S t r i n g   a [ ] ) {  
 	 	 n e w   M a z e H o m e ( ) ;  
 	 }  
 	 c l a s s   S h o w M o u s e L i s t e n e r   i m p l e m e n t s   A c t i o n L i s t e n e r   {  
 	 	  
  
 	  
 	 	 p u b l i c   v o i d   a c t i o n P e r f o r m e d ( A c t i o n E v e n t   a r g 0 )   {  
 	 	 	  
 	 	 	 n e w   S h o w M a z e 2 ( h o m e ) ;  
 	 	 	 h o m e . s e t V i s i b l e ( f a l s e ) ;  
 	 	  
 	 	 }  
  
 	  
 	 }  
 	 c l a s s   E x i t M o u s e L i s t e n e r   i m p l e m e n t s   A c t i o n L i s t e n e r   {  
 	 	  
  
 	 	  
 	 	 p u b l i c   v o i d   a c t i o n P e r f o r m e d ( A c t i o n E v e n t   a r g 0 )   {  
 	 	 	  
 	 	 	 S y s t e m . e x i t ( 0 ) ;  
 	 	  
 	 	 }  
  
 	  
 	 }  
  
 }  
 