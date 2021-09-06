/*
import com.google.inject.AbstractModule;
import com.google.inject.BindingAnnotation;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.logging.Logger;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

@BindingAnnotation @Target({ FIELD, PARAMETER, METHOD }) @Retention(RUNTIME)
@interface WinWord {}

public class Library {
   public static void main(String[] args) {
      Injector injector = Guice.createInjector(new TextEditorModule());
      TextEditor editor = injector.getInstance(TextEditor.class);
      editor.makeSpellCheck();     
   } 
}
//
//class TextEditor {
//   private SpellChecker spellChecker;   
//
//   @Inject
//   public TextEditor(@WinWord SpellChecker spellChecker) {
//      this.spellChecker = spellChecker;
//   }
//
//   public void makeSpellCheck(){
//      spellChecker.checkSpelling(); 
//   } 
//}

class TextEditor {
	   private Logger logger;   

	   @Inject
	   public TextEditor(Logger logger) {
	      this.logger = logger;
	   }

	   public void makeSpellCheck(){
	      logger.info("this is my information ");
	   } 
	}

////Binding Module
//class TextEditorModule extends AbstractModule {
//
//   @Override
//   protected void configure() {
//      bind(SpellChecker.class).annotatedWith(WinWord.class)
//         .to(WinWordSpellCheckerImpl.class);    
//   } 
//}

//Binding Module
class TextEditorModule extends AbstractModule {

 @Override
 protected void configure() {
 } 
}

//spell checker interface
interface SpellChecker {
   public void checkSpelling();
}

//spell checker implementation
class SpellCheckerImpl implements SpellChecker {

   @Override
   public void checkSpelling() {
      System.out.println("Inside checkSpelling." );
   } 
}

//subclass of SpellCheckerImpl
class WinWordSpellCheckerImpl extends SpellCheckerImpl{
   @Override
   public void checkSpelling() {
      System.out.println("inside winword spell checking" );
   } 
}
*/
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.matcher.Matchers;

public class Library {
   public static void main(String[] args) {
      Injector injector = Guice.createInjector(new TextEditorModule());
      TextEditor editor = injector.getInstance(TextEditor.class);
      editor.makeSpellCheck(); 
   } 
}

class TextEditor {
   private SpellChecker spellChecker;

   @Inject
   public TextEditor(SpellChecker spellChecker) {
      this.spellChecker = spellChecker;
   }

   public void makeSpellCheck(){
      spellChecker.checkSpelling();
   }
}

//Binding Module
class TextEditorModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(SpellChecker.class).to(SpellCheckerImpl.class);
      bindInterceptor(Matchers.any(), 
         Matchers.annotatedWith(Callk.class), 
         new CallTrackerService());
   } 
}

//spell checker interface
interface SpellChecker {
   public void checkSpelling();
}

//spell checker implementation
class SpellCheckerImpl implements SpellChecker {

   @Override @Callk
   public void checkSpelling() {
      System.out.println("Inside checkSpelling." );
   } 
}

@Retention(RetentionPolicy.RUNTIME) @Target(ElementType.METHOD)
@interface Callk {}

class CallTrackerService implements MethodInterceptor  {

	@Override
	public Object invoke(MethodInvocation Invocation) throws Throwable {
	System.out.println("your method will be executing now");
	Object result = Invocation.proceed();
	System.out.println("your method executed");
	return result;
	}
	
}