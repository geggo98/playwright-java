/*
 * Copyright (c) Microsoft Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.microsoft.playwright;

import com.microsoft.playwright.options.*;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * - extends: [EventEmitter]
 *
 * <p> Page provides methods to interact with a single tab in a {@code Browser}, or an
 * [extension background page](https://developer.chrome.com/extensions/background_pages) in Chromium. One {@code Browser}
 * instance might have multiple {@code Page} instances.
 *
 * <p> The Page class emits various events (described below) which can be handled using any of Node's native
 * [{@code EventEmitter}](https://nodejs.org/api/events.html#events_class_eventemitter) methods, such as {@code on}, {@code once} or
 * {@code removeListener}.
 *
 * <p> To unsubscribe from events use the {@code removeListener} method:
 */
public interface Page extends AutoCloseable {

  void onClose(Consumer<Page> handler);
  void offClose(Consumer<Page> handler);

  void onConsole(Consumer<ConsoleMessage> handler);
  void offConsole(Consumer<ConsoleMessage> handler);

  void onCrash(Consumer<Page> handler);
  void offCrash(Consumer<Page> handler);

  void onDialog(Consumer<Dialog> handler);
  void offDialog(Consumer<Dialog> handler);

  void onDOMContentLoaded(Consumer<Page> handler);
  void offDOMContentLoaded(Consumer<Page> handler);

  void onDownload(Consumer<Download> handler);
  void offDownload(Consumer<Download> handler);

  void onFileChooser(Consumer<FileChooser> handler);
  void offFileChooser(Consumer<FileChooser> handler);

  void onFrameAttached(Consumer<Frame> handler);
  void offFrameAttached(Consumer<Frame> handler);

  void onFrameDetached(Consumer<Frame> handler);
  void offFrameDetached(Consumer<Frame> handler);

  void onFrameNavigated(Consumer<Frame> handler);
  void offFrameNavigated(Consumer<Frame> handler);

  void onLoad(Consumer<Page> handler);
  void offLoad(Consumer<Page> handler);

  void onPageError(Consumer<String> handler);
  void offPageError(Consumer<String> handler);

  void onPopup(Consumer<Page> handler);
  void offPopup(Consumer<Page> handler);

  void onRequest(Consumer<Request> handler);
  void offRequest(Consumer<Request> handler);

  void onRequestFailed(Consumer<Request> handler);
  void offRequestFailed(Consumer<Request> handler);

  void onRequestFinished(Consumer<Request> handler);
  void offRequestFinished(Consumer<Request> handler);

  void onResponse(Consumer<Response> handler);
  void offResponse(Consumer<Response> handler);

  void onWebSocket(Consumer<WebSocket> handler);
  void offWebSocket(Consumer<WebSocket> handler);

  void onWorker(Consumer<Worker> handler);
  void offWorker(Consumer<Worker> handler);

  class AddScriptTagOptions {
    /**
     * Raw JavaScript content to be injected into frame.
     */
    public String content;
    /**
     * Path to the JavaScript file to be injected into frame. If {@code path} is a relative path, then it is resolved relative to the
     * current working directory.
     */
    public Path path;
    /**
     * Script type. Use 'module' in order to load a Javascript ES6 module. See
     * [script](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/script) for more details.
     */
    public String type;
    /**
     * URL of a script to be added.
     */
    public String url;

    public AddScriptTagOptions withContent(String content) {
      this.content = content;
      return this;
    }
    public AddScriptTagOptions withPath(Path path) {
      this.path = path;
      return this;
    }
    public AddScriptTagOptions withType(String type) {
      this.type = type;
      return this;
    }
    public AddScriptTagOptions withUrl(String url) {
      this.url = url;
      return this;
    }
  }
  class AddStyleTagOptions {
    /**
     * Raw CSS content to be injected into frame.
     */
    public String content;
    /**
     * Path to the CSS file to be injected into frame. If {@code path} is a relative path, then it is resolved relative to the
     * current working directory.
     */
    public Path path;
    /**
     * URL of the {@code <link>} tag.
     */
    public String url;

    public AddStyleTagOptions withContent(String content) {
      this.content = content;
      return this;
    }
    public AddStyleTagOptions withPath(Path path) {
      this.path = path;
      return this;
    }
    public AddStyleTagOptions withUrl(String url) {
      this.url = url;
      return this;
    }
  }
  class CheckOptions {
    /**
     * Whether to bypass the [actionability](./actionability.md) checks. Defaults to {@code false}.
     */
    public Boolean force;
    /**
     * Actions that initiate navigations are waiting for these navigations to happen and for pages to start loading. You can
     * opt out of waiting via setting this flag. You would only need this option in the exceptional cases such as navigating to
     * inaccessible pages. Defaults to {@code false}.
     */
    public Boolean noWaitAfter;
    /**
     * Maximum time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable timeout. The default value can be changed by
     * using the [{@code method: BrowserContext.setDefaultTimeout}] or [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;

    public CheckOptions withForce(boolean force) {
      this.force = force;
      return this;
    }
    public CheckOptions withNoWaitAfter(boolean noWaitAfter) {
      this.noWaitAfter = noWaitAfter;
      return this;
    }
    public CheckOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class ClickOptions {
    /**
     * Defaults to {@code left}.
     */
    public MouseButton button;
    /**
     * defaults to 1. See [UIEvent.detail].
     */
    public Integer clickCount;
    /**
     * Time to wait between {@code mousedown} and {@code mouseup} in milliseconds. Defaults to 0.
     */
    public Double delay;
    /**
     * Whether to bypass the [actionability](./actionability.md) checks. Defaults to {@code false}.
     */
    public Boolean force;
    /**
     * Modifier keys to press. Ensures that only these modifiers are pressed during the operation, and then restores current
     * modifiers back. If not specified, currently pressed modifiers are used.
     */
    public List<KeyboardModifier> modifiers;
    /**
     * Actions that initiate navigations are waiting for these navigations to happen and for pages to start loading. You can
     * opt out of waiting via setting this flag. You would only need this option in the exceptional cases such as navigating to
     * inaccessible pages. Defaults to {@code false}.
     */
    public Boolean noWaitAfter;
    /**
     * A point to use relative to the top-left corner of element padding box. If not specified, uses some visible point of the
     * element.
     */
    public Position position;
    /**
     * Maximum time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable timeout. The default value can be changed by
     * using the [{@code method: BrowserContext.setDefaultTimeout}] or [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;

    public ClickOptions withButton(MouseButton button) {
      this.button = button;
      return this;
    }
    public ClickOptions withClickCount(int clickCount) {
      this.clickCount = clickCount;
      return this;
    }
    public ClickOptions withDelay(double delay) {
      this.delay = delay;
      return this;
    }
    public ClickOptions withForce(boolean force) {
      this.force = force;
      return this;
    }
    public ClickOptions withModifiers(List<KeyboardModifier> modifiers) {
      this.modifiers = modifiers;
      return this;
    }
    public ClickOptions withNoWaitAfter(boolean noWaitAfter) {
      this.noWaitAfter = noWaitAfter;
      return this;
    }
    public ClickOptions withPosition(Position position) {
      this.position = position;
      return this;
    }
    public ClickOptions withPosition(double x, double y) {
      return withPosition(new Position(x, y));
    }
    public ClickOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class CloseOptions {
    /**
     * Defaults to {@code false}. Whether to run the
     * [before unload](https://developer.mozilla.org/en-US/docs/Web/Events/beforeunload) page handlers.
     */
    public Boolean runBeforeUnload;

    public CloseOptions withRunBeforeUnload(boolean runBeforeUnload) {
      this.runBeforeUnload = runBeforeUnload;
      return this;
    }
  }
  class DblclickOptions {
    /**
     * Defaults to {@code left}.
     */
    public MouseButton button;
    /**
     * Time to wait between {@code mousedown} and {@code mouseup} in milliseconds. Defaults to 0.
     */
    public Double delay;
    /**
     * Whether to bypass the [actionability](./actionability.md) checks. Defaults to {@code false}.
     */
    public Boolean force;
    /**
     * Modifier keys to press. Ensures that only these modifiers are pressed during the operation, and then restores current
     * modifiers back. If not specified, currently pressed modifiers are used.
     */
    public List<KeyboardModifier> modifiers;
    /**
     * Actions that initiate navigations are waiting for these navigations to happen and for pages to start loading. You can
     * opt out of waiting via setting this flag. You would only need this option in the exceptional cases such as navigating to
     * inaccessible pages. Defaults to {@code false}.
     */
    public Boolean noWaitAfter;
    /**
     * A point to use relative to the top-left corner of element padding box. If not specified, uses some visible point of the
     * element.
     */
    public Position position;
    /**
     * Maximum time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable timeout. The default value can be changed by
     * using the [{@code method: BrowserContext.setDefaultTimeout}] or [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;

    public DblclickOptions withButton(MouseButton button) {
      this.button = button;
      return this;
    }
    public DblclickOptions withDelay(double delay) {
      this.delay = delay;
      return this;
    }
    public DblclickOptions withForce(boolean force) {
      this.force = force;
      return this;
    }
    public DblclickOptions withModifiers(List<KeyboardModifier> modifiers) {
      this.modifiers = modifiers;
      return this;
    }
    public DblclickOptions withNoWaitAfter(boolean noWaitAfter) {
      this.noWaitAfter = noWaitAfter;
      return this;
    }
    public DblclickOptions withPosition(Position position) {
      this.position = position;
      return this;
    }
    public DblclickOptions withPosition(double x, double y) {
      return withPosition(new Position(x, y));
    }
    public DblclickOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class DispatchEventOptions {
    /**
     * Maximum time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable timeout. The default value can be changed by
     * using the [{@code method: BrowserContext.setDefaultTimeout}] or [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;

    public DispatchEventOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class EmulateMediaOptions {
    /**
     * Emulates {@code 'prefers-colors-scheme'} media feature, supported values are {@code 'light'}, {@code 'dark'}, {@code 'no-preference'}. Passing
     * {@code null} disables color scheme emulation.
     */
    public Optional<ColorScheme> colorScheme;
    /**
     * Changes the CSS media type of the page. The only allowed values are {@code 'screen'}, {@code 'print'} and {@code null}. Passing {@code null}
     * disables CSS media emulation.
     */
    public Optional<Media> media;

    public EmulateMediaOptions withColorScheme(ColorScheme colorScheme) {
      this.colorScheme = Optional.ofNullable(colorScheme);
      return this;
    }
    public EmulateMediaOptions withMedia(Media media) {
      this.media = Optional.ofNullable(media);
      return this;
    }
  }
  class ExposeBindingOptions {
    /**
     * Whether to pass the argument as a handle, instead of passing by value. When passing a handle, only one argument is
     * supported. When passing by value, multiple arguments are supported.
     */
    public Boolean handle;

    public ExposeBindingOptions withHandle(boolean handle) {
      this.handle = handle;
      return this;
    }
  }
  class FillOptions {
    /**
     * Actions that initiate navigations are waiting for these navigations to happen and for pages to start loading. You can
     * opt out of waiting via setting this flag. You would only need this option in the exceptional cases such as navigating to
     * inaccessible pages. Defaults to {@code false}.
     */
    public Boolean noWaitAfter;
    /**
     * Maximum time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable timeout. The default value can be changed by
     * using the [{@code method: BrowserContext.setDefaultTimeout}] or [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;

    public FillOptions withNoWaitAfter(boolean noWaitAfter) {
      this.noWaitAfter = noWaitAfter;
      return this;
    }
    public FillOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class FocusOptions {
    /**
     * Maximum time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable timeout. The default value can be changed by
     * using the [{@code method: BrowserContext.setDefaultTimeout}] or [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;

    public FocusOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class GetAttributeOptions {
    /**
     * Maximum time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable timeout. The default value can be changed by
     * using the [{@code method: BrowserContext.setDefaultTimeout}] or [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;

    public GetAttributeOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class GoBackOptions {
    /**
     * Maximum operation time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable timeout. The default value can be
     * changed by using the [{@code method: BrowserContext.setDefaultNavigationTimeout}],
     * [{@code method: BrowserContext.setDefaultTimeout}], [{@code method: Page.setDefaultNavigationTimeout}] or
     * [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;
    /**
     * When to consider operation succeeded, defaults to {@code load}. Events can be either:
     * - {@code 'domcontentloaded'} - consider operation to be finished when the {@code DOMContentLoaded} event is fired.
     * - {@code 'load'} - consider operation to be finished when the {@code load} event is fired.
     * - {@code 'networkidle'} - consider operation to be finished when there are no network connections for at least {@code 500} ms.
     */
    public WaitUntilState waitUntil;

    public GoBackOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
    public GoBackOptions withWaitUntil(WaitUntilState waitUntil) {
      this.waitUntil = waitUntil;
      return this;
    }
  }
  class GoForwardOptions {
    /**
     * Maximum operation time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable timeout. The default value can be
     * changed by using the [{@code method: BrowserContext.setDefaultNavigationTimeout}],
     * [{@code method: BrowserContext.setDefaultTimeout}], [{@code method: Page.setDefaultNavigationTimeout}] or
     * [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;
    /**
     * When to consider operation succeeded, defaults to {@code load}. Events can be either:
     * - {@code 'domcontentloaded'} - consider operation to be finished when the {@code DOMContentLoaded} event is fired.
     * - {@code 'load'} - consider operation to be finished when the {@code load} event is fired.
     * - {@code 'networkidle'} - consider operation to be finished when there are no network connections for at least {@code 500} ms.
     */
    public WaitUntilState waitUntil;

    public GoForwardOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
    public GoForwardOptions withWaitUntil(WaitUntilState waitUntil) {
      this.waitUntil = waitUntil;
      return this;
    }
  }
  class NavigateOptions {
    /**
     * Referer header value. If provided it will take preference over the referer header value set by
     * [{@code method: Page.setExtraHTTPHeaders}].
     */
    public String referer;
    /**
     * Maximum operation time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable timeout. The default value can be
     * changed by using the [{@code method: BrowserContext.setDefaultNavigationTimeout}],
     * [{@code method: BrowserContext.setDefaultTimeout}], [{@code method: Page.setDefaultNavigationTimeout}] or
     * [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;
    /**
     * When to consider operation succeeded, defaults to {@code load}. Events can be either:
     * - {@code 'domcontentloaded'} - consider operation to be finished when the {@code DOMContentLoaded} event is fired.
     * - {@code 'load'} - consider operation to be finished when the {@code load} event is fired.
     * - {@code 'networkidle'} - consider operation to be finished when there are no network connections for at least {@code 500} ms.
     */
    public WaitUntilState waitUntil;

    public NavigateOptions withReferer(String referer) {
      this.referer = referer;
      return this;
    }
    public NavigateOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
    public NavigateOptions withWaitUntil(WaitUntilState waitUntil) {
      this.waitUntil = waitUntil;
      return this;
    }
  }
  class HoverOptions {
    /**
     * Whether to bypass the [actionability](./actionability.md) checks. Defaults to {@code false}.
     */
    public Boolean force;
    /**
     * Modifier keys to press. Ensures that only these modifiers are pressed during the operation, and then restores current
     * modifiers back. If not specified, currently pressed modifiers are used.
     */
    public List<KeyboardModifier> modifiers;
    /**
     * A point to use relative to the top-left corner of element padding box. If not specified, uses some visible point of the
     * element.
     */
    public Position position;
    /**
     * Maximum time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable timeout. The default value can be changed by
     * using the [{@code method: BrowserContext.setDefaultTimeout}] or [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;

    public HoverOptions withForce(boolean force) {
      this.force = force;
      return this;
    }
    public HoverOptions withModifiers(List<KeyboardModifier> modifiers) {
      this.modifiers = modifiers;
      return this;
    }
    public HoverOptions withPosition(Position position) {
      this.position = position;
      return this;
    }
    public HoverOptions withPosition(double x, double y) {
      return withPosition(new Position(x, y));
    }
    public HoverOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class InnerHTMLOptions {
    /**
     * Maximum time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable timeout. The default value can be changed by
     * using the [{@code method: BrowserContext.setDefaultTimeout}] or [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;

    public InnerHTMLOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class InnerTextOptions {
    /**
     * Maximum time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable timeout. The default value can be changed by
     * using the [{@code method: BrowserContext.setDefaultTimeout}] or [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;

    public InnerTextOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class IsCheckedOptions {
    /**
     * Maximum time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable timeout. The default value can be changed by
     * using the [{@code method: BrowserContext.setDefaultTimeout}] or [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;

    public IsCheckedOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class IsDisabledOptions {
    /**
     * Maximum time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable timeout. The default value can be changed by
     * using the [{@code method: BrowserContext.setDefaultTimeout}] or [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;

    public IsDisabledOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class IsEditableOptions {
    /**
     * Maximum time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable timeout. The default value can be changed by
     * using the [{@code method: BrowserContext.setDefaultTimeout}] or [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;

    public IsEditableOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class IsEnabledOptions {
    /**
     * Maximum time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable timeout. The default value can be changed by
     * using the [{@code method: BrowserContext.setDefaultTimeout}] or [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;

    public IsEnabledOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class IsHiddenOptions {
    /**
     * Maximum time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable timeout. The default value can be changed by
     * using the [{@code method: BrowserContext.setDefaultTimeout}] or [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;

    public IsHiddenOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class IsVisibleOptions {
    /**
     * Maximum time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable timeout. The default value can be changed by
     * using the [{@code method: BrowserContext.setDefaultTimeout}] or [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;

    public IsVisibleOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class PdfOptions {
    /**
     * Display header and footer. Defaults to {@code false}.
     */
    public Boolean displayHeaderFooter;
    /**
     * HTML template for the print footer. Should use the same format as the {@code headerTemplate}.
     */
    public String footerTemplate;
    /**
     * Paper format. If set, takes priority over {@code width} or {@code height} options. Defaults to 'Letter'.
     */
    public String format;
    /**
     * HTML template for the print header. Should be valid HTML markup with following classes used to inject printing values
     * into them:
     * - {@code 'date'} formatted print date
     * - {@code 'title'} document title
     * - {@code 'url'} document location
     * - {@code 'pageNumber'} current page number
     * - {@code 'totalPages'} total pages in the document
     */
    public String headerTemplate;
    /**
     * Paper height, accepts values labeled with units.
     */
    public String height;
    /**
     * Paper orientation. Defaults to {@code false}.
     */
    public Boolean landscape;
    /**
     * Paper margins, defaults to none.
     */
    public Margin margin;
    /**
     * Paper ranges to print, e.g., '1-5, 8, 11-13'. Defaults to the empty string, which means print all pages.
     */
    public String pageRanges;
    /**
     * The file path to save the PDF to. If {@code path} is a relative path, then it is resolved relative to the current working
     * directory. If no path is provided, the PDF won't be saved to the disk.
     */
    public Path path;
    /**
     * Give any CSS {@code @page} size declared in the page priority over what is declared in {@code width} and {@code height} or {@code format}
     * options. Defaults to {@code false}, which will scale the content to fit the paper size.
     */
    public Boolean preferCSSPageSize;
    /**
     * Print background graphics. Defaults to {@code false}.
     */
    public Boolean printBackground;
    /**
     * Scale of the webpage rendering. Defaults to {@code 1}. Scale amount must be between 0.1 and 2.
     */
    public Double scale;
    /**
     * Paper width, accepts values labeled with units.
     */
    public String width;

    public PdfOptions withDisplayHeaderFooter(boolean displayHeaderFooter) {
      this.displayHeaderFooter = displayHeaderFooter;
      return this;
    }
    public PdfOptions withFooterTemplate(String footerTemplate) {
      this.footerTemplate = footerTemplate;
      return this;
    }
    public PdfOptions withFormat(String format) {
      this.format = format;
      return this;
    }
    public PdfOptions withHeaderTemplate(String headerTemplate) {
      this.headerTemplate = headerTemplate;
      return this;
    }
    public PdfOptions withHeight(String height) {
      this.height = height;
      return this;
    }
    public PdfOptions withLandscape(boolean landscape) {
      this.landscape = landscape;
      return this;
    }
    public PdfOptions withMargin(Margin margin) {
      this.margin = margin;
      return this;
    }
    public PdfOptions withPageRanges(String pageRanges) {
      this.pageRanges = pageRanges;
      return this;
    }
    public PdfOptions withPath(Path path) {
      this.path = path;
      return this;
    }
    public PdfOptions withPreferCSSPageSize(boolean preferCSSPageSize) {
      this.preferCSSPageSize = preferCSSPageSize;
      return this;
    }
    public PdfOptions withPrintBackground(boolean printBackground) {
      this.printBackground = printBackground;
      return this;
    }
    public PdfOptions withScale(double scale) {
      this.scale = scale;
      return this;
    }
    public PdfOptions withWidth(String width) {
      this.width = width;
      return this;
    }
  }
  class PressOptions {
    /**
     * Time to wait between {@code keydown} and {@code keyup} in milliseconds. Defaults to 0.
     */
    public Double delay;
    /**
     * Actions that initiate navigations are waiting for these navigations to happen and for pages to start loading. You can
     * opt out of waiting via setting this flag. You would only need this option in the exceptional cases such as navigating to
     * inaccessible pages. Defaults to {@code false}.
     */
    public Boolean noWaitAfter;
    /**
     * Maximum time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable timeout. The default value can be changed by
     * using the [{@code method: BrowserContext.setDefaultTimeout}] or [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;

    public PressOptions withDelay(double delay) {
      this.delay = delay;
      return this;
    }
    public PressOptions withNoWaitAfter(boolean noWaitAfter) {
      this.noWaitAfter = noWaitAfter;
      return this;
    }
    public PressOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class ReloadOptions {
    /**
     * Maximum operation time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable timeout. The default value can be
     * changed by using the [{@code method: BrowserContext.setDefaultNavigationTimeout}],
     * [{@code method: BrowserContext.setDefaultTimeout}], [{@code method: Page.setDefaultNavigationTimeout}] or
     * [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;
    /**
     * When to consider operation succeeded, defaults to {@code load}. Events can be either:
     * - {@code 'domcontentloaded'} - consider operation to be finished when the {@code DOMContentLoaded} event is fired.
     * - {@code 'load'} - consider operation to be finished when the {@code load} event is fired.
     * - {@code 'networkidle'} - consider operation to be finished when there are no network connections for at least {@code 500} ms.
     */
    public WaitUntilState waitUntil;

    public ReloadOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
    public ReloadOptions withWaitUntil(WaitUntilState waitUntil) {
      this.waitUntil = waitUntil;
      return this;
    }
  }
  class ScreenshotOptions {
    /**
     * An object which specifies clipping of the resulting image. Should have the following fields:
     */
    public Clip clip;
    /**
     * When true, takes a screenshot of the full scrollable page, instead of the currently visible viewport. Defaults to
     * {@code false}.
     */
    public Boolean fullPage;
    /**
     * Hides default white background and allows capturing screenshots with transparency. Not applicable to {@code jpeg} images.
     * Defaults to {@code false}.
     */
    public Boolean omitBackground;
    /**
     * The file path to save the image to. The screenshot type will be inferred from file extension. If {@code path} is a relative
     * path, then it is resolved relative to the current working directory. If no path is provided, the image won't be saved to
     * the disk.
     */
    public Path path;
    /**
     * The quality of the image, between 0-100. Not applicable to {@code png} images.
     */
    public Integer quality;
    /**
     * Maximum time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable timeout. The default value can be changed by
     * using the [{@code method: BrowserContext.setDefaultTimeout}] or [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;
    /**
     * Specify screenshot type, defaults to {@code png}.
     */
    public ScreenshotType type;

    public ScreenshotOptions withClip(Clip clip) {
      this.clip = clip;
      return this;
    }
    public ScreenshotOptions withFullPage(boolean fullPage) {
      this.fullPage = fullPage;
      return this;
    }
    public ScreenshotOptions withOmitBackground(boolean omitBackground) {
      this.omitBackground = omitBackground;
      return this;
    }
    public ScreenshotOptions withPath(Path path) {
      this.path = path;
      return this;
    }
    public ScreenshotOptions withQuality(int quality) {
      this.quality = quality;
      return this;
    }
    public ScreenshotOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
    public ScreenshotOptions withType(ScreenshotType type) {
      this.type = type;
      return this;
    }
  }
  class SelectOptionOptions {
    /**
     * Actions that initiate navigations are waiting for these navigations to happen and for pages to start loading. You can
     * opt out of waiting via setting this flag. You would only need this option in the exceptional cases such as navigating to
     * inaccessible pages. Defaults to {@code false}.
     */
    public Boolean noWaitAfter;
    /**
     * Maximum time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable timeout. The default value can be changed by
     * using the [{@code method: BrowserContext.setDefaultTimeout}] or [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;

    public SelectOptionOptions withNoWaitAfter(boolean noWaitAfter) {
      this.noWaitAfter = noWaitAfter;
      return this;
    }
    public SelectOptionOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class SetContentOptions {
    /**
     * Maximum operation time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable timeout. The default value can be
     * changed by using the [{@code method: BrowserContext.setDefaultNavigationTimeout}],
     * [{@code method: BrowserContext.setDefaultTimeout}], [{@code method: Page.setDefaultNavigationTimeout}] or
     * [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;
    /**
     * When to consider operation succeeded, defaults to {@code load}. Events can be either:
     * - {@code 'domcontentloaded'} - consider operation to be finished when the {@code DOMContentLoaded} event is fired.
     * - {@code 'load'} - consider operation to be finished when the {@code load} event is fired.
     * - {@code 'networkidle'} - consider operation to be finished when there are no network connections for at least {@code 500} ms.
     */
    public WaitUntilState waitUntil;

    public SetContentOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
    public SetContentOptions withWaitUntil(WaitUntilState waitUntil) {
      this.waitUntil = waitUntil;
      return this;
    }
  }
  class SetInputFilesOptions {
    /**
     * Actions that initiate navigations are waiting for these navigations to happen and for pages to start loading. You can
     * opt out of waiting via setting this flag. You would only need this option in the exceptional cases such as navigating to
     * inaccessible pages. Defaults to {@code false}.
     */
    public Boolean noWaitAfter;
    /**
     * Maximum time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable timeout. The default value can be changed by
     * using the [{@code method: BrowserContext.setDefaultTimeout}] or [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;

    public SetInputFilesOptions withNoWaitAfter(boolean noWaitAfter) {
      this.noWaitAfter = noWaitAfter;
      return this;
    }
    public SetInputFilesOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class TapOptions {
    /**
     * Whether to bypass the [actionability](./actionability.md) checks. Defaults to {@code false}.
     */
    public Boolean force;
    /**
     * Modifier keys to press. Ensures that only these modifiers are pressed during the operation, and then restores current
     * modifiers back. If not specified, currently pressed modifiers are used.
     */
    public List<KeyboardModifier> modifiers;
    /**
     * Actions that initiate navigations are waiting for these navigations to happen and for pages to start loading. You can
     * opt out of waiting via setting this flag. You would only need this option in the exceptional cases such as navigating to
     * inaccessible pages. Defaults to {@code false}.
     */
    public Boolean noWaitAfter;
    /**
     * A point to use relative to the top-left corner of element padding box. If not specified, uses some visible point of the
     * element.
     */
    public Position position;
    /**
     * Maximum time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable timeout. The default value can be changed by
     * using the [{@code method: BrowserContext.setDefaultTimeout}] or [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;

    public TapOptions withForce(boolean force) {
      this.force = force;
      return this;
    }
    public TapOptions withModifiers(List<KeyboardModifier> modifiers) {
      this.modifiers = modifiers;
      return this;
    }
    public TapOptions withNoWaitAfter(boolean noWaitAfter) {
      this.noWaitAfter = noWaitAfter;
      return this;
    }
    public TapOptions withPosition(Position position) {
      this.position = position;
      return this;
    }
    public TapOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class TextContentOptions {
    /**
     * Maximum time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable timeout. The default value can be changed by
     * using the [{@code method: BrowserContext.setDefaultTimeout}] or [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;

    public TextContentOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class TypeOptions {
    /**
     * Time to wait between key presses in milliseconds. Defaults to 0.
     */
    public Double delay;
    /**
     * Actions that initiate navigations are waiting for these navigations to happen and for pages to start loading. You can
     * opt out of waiting via setting this flag. You would only need this option in the exceptional cases such as navigating to
     * inaccessible pages. Defaults to {@code false}.
     */
    public Boolean noWaitAfter;
    /**
     * Maximum time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable timeout. The default value can be changed by
     * using the [{@code method: BrowserContext.setDefaultTimeout}] or [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;

    public TypeOptions withDelay(double delay) {
      this.delay = delay;
      return this;
    }
    public TypeOptions withNoWaitAfter(boolean noWaitAfter) {
      this.noWaitAfter = noWaitAfter;
      return this;
    }
    public TypeOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class UncheckOptions {
    /**
     * Whether to bypass the [actionability](./actionability.md) checks. Defaults to {@code false}.
     */
    public Boolean force;
    /**
     * Actions that initiate navigations are waiting for these navigations to happen and for pages to start loading. You can
     * opt out of waiting via setting this flag. You would only need this option in the exceptional cases such as navigating to
     * inaccessible pages. Defaults to {@code false}.
     */
    public Boolean noWaitAfter;
    /**
     * Maximum time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable timeout. The default value can be changed by
     * using the [{@code method: BrowserContext.setDefaultTimeout}] or [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;

    public UncheckOptions withForce(boolean force) {
      this.force = force;
      return this;
    }
    public UncheckOptions withNoWaitAfter(boolean noWaitAfter) {
      this.noWaitAfter = noWaitAfter;
      return this;
    }
    public UncheckOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class WaitForCloseOptions {
    /**
     * Maximum time to wait for in milliseconds. Defaults to {@code 30000} (30 seconds). Pass {@code 0} to disable timeout. The default
     * value can be changed by using the [{@code method: BrowserContext.setDefaultTimeout}].
     */
    public Double timeout;

    public WaitForCloseOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class WaitForConsoleMessageOptions {
    /**
     * Receives the {@code ConsoleMessage} object and resolves to truthy value when the waiting should resolve.
     */
    public Predicate<ConsoleMessage> predicate;
    /**
     * Maximum time to wait for in milliseconds. Defaults to {@code 30000} (30 seconds). Pass {@code 0} to disable timeout. The default
     * value can be changed by using the [{@code method: BrowserContext.setDefaultTimeout}].
     */
    public Double timeout;

    public WaitForConsoleMessageOptions withPredicate(Predicate<ConsoleMessage> predicate) {
      this.predicate = predicate;
      return this;
    }
    public WaitForConsoleMessageOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class WaitForDownloadOptions {
    /**
     * Receives the {@code Download} object and resolves to truthy value when the waiting should resolve.
     */
    public Predicate<Download> predicate;
    /**
     * Maximum time to wait for in milliseconds. Defaults to {@code 30000} (30 seconds). Pass {@code 0} to disable timeout. The default
     * value can be changed by using the [{@code method: BrowserContext.setDefaultTimeout}].
     */
    public Double timeout;

    public WaitForDownloadOptions withPredicate(Predicate<Download> predicate) {
      this.predicate = predicate;
      return this;
    }
    public WaitForDownloadOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class WaitForFileChooserOptions {
    /**
     * Receives the {@code FileChooser} object and resolves to truthy value when the waiting should resolve.
     */
    public Predicate<FileChooser> predicate;
    /**
     * Maximum time to wait for in milliseconds. Defaults to {@code 30000} (30 seconds). Pass {@code 0} to disable timeout. The default
     * value can be changed by using the [{@code method: BrowserContext.setDefaultTimeout}].
     */
    public Double timeout;

    public WaitForFileChooserOptions withPredicate(Predicate<FileChooser> predicate) {
      this.predicate = predicate;
      return this;
    }
    public WaitForFileChooserOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class WaitForFunctionOptions {
    /**
     * If {@code polling} is {@code 'raf'}, then {@code expression} is constantly executed in {@code requestAnimationFrame} callback. If {@code polling} is a
     * number, then it is treated as an interval in milliseconds at which the function would be executed. Defaults to {@code raf}.
     */
    public Integer pollingInterval;
    /**
     * maximum time to wait for in milliseconds. Defaults to {@code 30000} (30 seconds). Pass {@code 0} to disable timeout. The default
     * value can be changed by using the [{@code method: BrowserContext.setDefaultTimeout}].
     */
    public Double timeout;

    public WaitForFunctionOptions withRequestAnimationFrame() {
      this.pollingInterval = null;
      return this;
    }
    public WaitForFunctionOptions withPollingInterval(int millis) {
      this.pollingInterval = millis;
      return this;
    }
    public WaitForFunctionOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class WaitForLoadStateOptions {
    /**
     * Maximum operation time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable timeout. The default value can be
     * changed by using the [{@code method: BrowserContext.setDefaultNavigationTimeout}],
     * [{@code method: BrowserContext.setDefaultTimeout}], [{@code method: Page.setDefaultNavigationTimeout}] or
     * [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;

    public WaitForLoadStateOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class WaitForNavigationOptions {
    /**
     * Maximum operation time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable timeout. The default value can be
     * changed by using the [{@code method: BrowserContext.setDefaultNavigationTimeout}],
     * [{@code method: BrowserContext.setDefaultTimeout}], [{@code method: Page.setDefaultNavigationTimeout}] or
     * [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;
    /**
     * A glob pattern, regex pattern or predicate receiving [URL] to match while waiting for the navigation.
     */
    public String glob;
    public Pattern pattern;
    public Predicate<String> predicate;
    /**
     * When to consider operation succeeded, defaults to {@code load}. Events can be either:
     * - {@code 'domcontentloaded'} - consider operation to be finished when the {@code DOMContentLoaded} event is fired.
     * - {@code 'load'} - consider operation to be finished when the {@code load} event is fired.
     * - {@code 'networkidle'} - consider operation to be finished when there are no network connections for at least {@code 500} ms.
     */
    public WaitUntilState waitUntil;

    public WaitForNavigationOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
    public WaitForNavigationOptions withUrl(String glob) {
      this.glob = glob;
      return this;
    }
    public WaitForNavigationOptions withUrl(Pattern pattern) {
      this.pattern = pattern;
      return this;
    }
    public WaitForNavigationOptions withUrl(Predicate<String> predicate) {
      this.predicate = predicate;
      return this;
    }
    public WaitForNavigationOptions withWaitUntil(WaitUntilState waitUntil) {
      this.waitUntil = waitUntil;
      return this;
    }
  }
  class WaitForPopupOptions {
    /**
     * Receives the {@code Page} object and resolves to truthy value when the waiting should resolve.
     */
    public Predicate<Page> predicate;
    /**
     * Maximum time to wait for in milliseconds. Defaults to {@code 30000} (30 seconds). Pass {@code 0} to disable timeout. The default
     * value can be changed by using the [{@code method: BrowserContext.setDefaultTimeout}].
     */
    public Double timeout;

    public WaitForPopupOptions withPredicate(Predicate<Page> predicate) {
      this.predicate = predicate;
      return this;
    }
    public WaitForPopupOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class WaitForRequestOptions {
    /**
     * Maximum wait time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable the timeout. The default value can be
     * changed by using the [{@code method: Page.setDefaultTimeout}] method.
     */
    public Double timeout;

    public WaitForRequestOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class WaitForResponseOptions {
    /**
     * Maximum wait time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable the timeout. The default value can be
     * changed by using the [{@code method: BrowserContext.setDefaultTimeout}] or [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;

    public WaitForResponseOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class WaitForSelectorOptions {
    /**
     * Defaults to {@code 'visible'}. Can be either:
     * - {@code 'attached'} - wait for element to be present in DOM.
     * - {@code 'detached'} - wait for element to not be present in DOM.
     * - {@code 'visible'} - wait for element to have non-empty bounding box and no {@code visibility:hidden}. Note that element without
     *   any content or with {@code display:none} has an empty bounding box and is not considered visible.
     * - {@code 'hidden'} - wait for element to be either detached from DOM, or have an empty bounding box or {@code visibility:hidden}.
     *   This is opposite to the {@code 'visible'} option.
     */
    public WaitForSelectorState state;
    /**
     * Maximum time in milliseconds, defaults to 30 seconds, pass {@code 0} to disable timeout. The default value can be changed by
     * using the [{@code method: BrowserContext.setDefaultTimeout}] or [{@code method: Page.setDefaultTimeout}] methods.
     */
    public Double timeout;

    public WaitForSelectorOptions withState(WaitForSelectorState state) {
      this.state = state;
      return this;
    }
    public WaitForSelectorOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class WaitForWebSocketOptions {
    /**
     * Receives the {@code WebSocket} object and resolves to truthy value when the waiting should resolve.
     */
    public Predicate<WebSocket> predicate;
    /**
     * Maximum time to wait for in milliseconds. Defaults to {@code 30000} (30 seconds). Pass {@code 0} to disable timeout. The default
     * value can be changed by using the [{@code method: BrowserContext.setDefaultTimeout}].
     */
    public Double timeout;

    public WaitForWebSocketOptions withPredicate(Predicate<WebSocket> predicate) {
      this.predicate = predicate;
      return this;
    }
    public WaitForWebSocketOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  class WaitForWorkerOptions {
    /**
     * Receives the {@code Worker} object and resolves to truthy value when the waiting should resolve.
     */
    public Predicate<Worker> predicate;
    /**
     * Maximum time to wait for in milliseconds. Defaults to {@code 30000} (30 seconds). Pass {@code 0} to disable timeout. The default
     * value can be changed by using the [{@code method: BrowserContext.setDefaultTimeout}].
     */
    public Double timeout;

    public WaitForWorkerOptions withPredicate(Predicate<Worker> predicate) {
      this.predicate = predicate;
      return this;
    }
    public WaitForWorkerOptions withTimeout(double timeout) {
      this.timeout = timeout;
      return this;
    }
  }
  Accessibility accessibility();
  /**
   * Adds a script which would be evaluated in one of the following scenarios:
   * - Whenever the page is navigated.
   * - Whenever the child frame is attached or navigated. In this case, the script is evaluated in the context of the newly
   *   attached frame.
   *
   * <p> The script is evaluated after the document was created but before any of its scripts were run. This is useful to amend
   * the JavaScript environment, e.g. to seed {@code Math.random}.
   *
   * <p> <strong>NOTE:</strong> The order of evaluation of multiple scripts installed via [{@code method: BrowserContext.addInitScript}] and
   * [{@code method: Page.addInitScript}] is not defined.
   *
   * @param script Script to be evaluated in all pages in the browser context.
   */
  void addInitScript(String script);
  /**
   * Adds a script which would be evaluated in one of the following scenarios:
   * - Whenever the page is navigated.
   * - Whenever the child frame is attached or navigated. In this case, the script is evaluated in the context of the newly
   *   attached frame.
   *
   * <p> The script is evaluated after the document was created but before any of its scripts were run. This is useful to amend
   * the JavaScript environment, e.g. to seed {@code Math.random}.
   *
   * <p> <strong>NOTE:</strong> The order of evaluation of multiple scripts installed via [{@code method: BrowserContext.addInitScript}] and
   * [{@code method: Page.addInitScript}] is not defined.
   *
   * @param script Script to be evaluated in all pages in the browser context.
   */
  void addInitScript(Path script);
  /**
   * Adds a {@code <script>} tag into the page with the desired url or content. Returns the added tag when the script's onload
   * fires or when the script content was injected into frame.
   *
   * <p> Shortcut for main frame's [{@code method: Frame.addScriptTag}].
   */
  default ElementHandle addScriptTag() {
    return addScriptTag(null);
  }
  /**
   * Adds a {@code <script>} tag into the page with the desired url or content. Returns the added tag when the script's onload
   * fires or when the script content was injected into frame.
   *
   * <p> Shortcut for main frame's [{@code method: Frame.addScriptTag}].
   */
  ElementHandle addScriptTag(AddScriptTagOptions options);
  /**
   * Adds a {@code <link rel="stylesheet">} tag into the page with the desired url or a {@code <style type="text/css">} tag with the
   * content. Returns the added tag when the stylesheet's onload fires or when the CSS content was injected into frame.
   *
   * <p> Shortcut for main frame's [{@code method: Frame.addStyleTag}].
   */
  default ElementHandle addStyleTag() {
    return addStyleTag(null);
  }
  /**
   * Adds a {@code <link rel="stylesheet">} tag into the page with the desired url or a {@code <style type="text/css">} tag with the
   * content. Returns the added tag when the stylesheet's onload fires or when the CSS content was injected into frame.
   *
   * <p> Shortcut for main frame's [{@code method: Frame.addStyleTag}].
   */
  ElementHandle addStyleTag(AddStyleTagOptions options);
  /**
   * Brings page to front (activates tab).
   */
  void bringToFront();
  /**
   * This method checks an element matching {@code selector} by performing the following steps:
   * 1. Find an element match matching {@code selector}. If there is none, wait until a matching element is attached to the DOM.
   * 1. Ensure that matched element is a checkbox or a radio input. If not, this method rejects. If the element is already
   *    checked, this method returns immediately.
   * 1. Wait for [actionability](./actionability.md) checks on the matched element, unless {@code force} option is set. If the
   *    element is detached during the checks, the whole action is retried.
   * 1. Scroll the element into view if needed.
   * 1. Use [{@code property: Page.mouse}] to click in the center of the element.
   * 1. Wait for initiated navigations to either succeed or fail, unless {@code noWaitAfter} option is set.
   * 1. Ensure that the element is now checked. If not, this method rejects.
   *
   * <p> When all steps combined have not finished during the specified {@code timeout}, this method rejects with a {@code TimeoutError}.
   * Passing zero timeout disables this.
   *
   * <p> Shortcut for main frame's [{@code method: Frame.check}].
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  default void check(String selector) {
    check(selector, null);
  }
  /**
   * This method checks an element matching {@code selector} by performing the following steps:
   * 1. Find an element match matching {@code selector}. If there is none, wait until a matching element is attached to the DOM.
   * 1. Ensure that matched element is a checkbox or a radio input. If not, this method rejects. If the element is already
   *    checked, this method returns immediately.
   * 1. Wait for [actionability](./actionability.md) checks on the matched element, unless {@code force} option is set. If the
   *    element is detached during the checks, the whole action is retried.
   * 1. Scroll the element into view if needed.
   * 1. Use [{@code property: Page.mouse}] to click in the center of the element.
   * 1. Wait for initiated navigations to either succeed or fail, unless {@code noWaitAfter} option is set.
   * 1. Ensure that the element is now checked. If not, this method rejects.
   *
   * <p> When all steps combined have not finished during the specified {@code timeout}, this method rejects with a {@code TimeoutError}.
   * Passing zero timeout disables this.
   *
   * <p> Shortcut for main frame's [{@code method: Frame.check}].
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  void check(String selector, CheckOptions options);
  /**
   * This method clicks an element matching {@code selector} by performing the following steps:
   * 1. Find an element match matching {@code selector}. If there is none, wait until a matching element is attached to the DOM.
   * 1. Wait for [actionability](./actionability.md) checks on the matched element, unless {@code force} option is set. If the
   *    element is detached during the checks, the whole action is retried.
   * 1. Scroll the element into view if needed.
   * 1. Use [{@code property: Page.mouse}] to click in the center of the element, or the specified {@code position}.
   * 1. Wait for initiated navigations to either succeed or fail, unless {@code noWaitAfter} option is set.
   *
   * <p> When all steps combined have not finished during the specified {@code timeout}, this method rejects with a {@code TimeoutError}.
   * Passing zero timeout disables this.
   *
   * <p> Shortcut for main frame's [{@code method: Frame.click}].
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  default void click(String selector) {
    click(selector, null);
  }
  /**
   * This method clicks an element matching {@code selector} by performing the following steps:
   * 1. Find an element match matching {@code selector}. If there is none, wait until a matching element is attached to the DOM.
   * 1. Wait for [actionability](./actionability.md) checks on the matched element, unless {@code force} option is set. If the
   *    element is detached during the checks, the whole action is retried.
   * 1. Scroll the element into view if needed.
   * 1. Use [{@code property: Page.mouse}] to click in the center of the element, or the specified {@code position}.
   * 1. Wait for initiated navigations to either succeed or fail, unless {@code noWaitAfter} option is set.
   *
   * <p> When all steps combined have not finished during the specified {@code timeout}, this method rejects with a {@code TimeoutError}.
   * Passing zero timeout disables this.
   *
   * <p> Shortcut for main frame's [{@code method: Frame.click}].
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  void click(String selector, ClickOptions options);
  /**
   * If {@code runBeforeUnload} is {@code false}, does not run any unload handlers and waits for the page to be closed. If
   * {@code runBeforeUnload} is {@code true} the method will run unload handlers, but will **not** wait for the page to close.
   *
   * <p> By default, {@code page.close()} **does not** run {@code beforeunload} handlers.
   *
   * <p> <strong>NOTE:</strong> if {@code runBeforeUnload} is passed as true, a {@code beforeunload} dialog might be summoned and should be handled manually
   * via [{@code event: Page.dialog}] event.
   */
  default void close() {
    close(null);
  }
  /**
   * If {@code runBeforeUnload} is {@code false}, does not run any unload handlers and waits for the page to be closed. If
   * {@code runBeforeUnload} is {@code true} the method will run unload handlers, but will **not** wait for the page to close.
   *
   * <p> By default, {@code page.close()} **does not** run {@code beforeunload} handlers.
   *
   * <p> <strong>NOTE:</strong> if {@code runBeforeUnload} is passed as true, a {@code beforeunload} dialog might be summoned and should be handled manually
   * via [{@code event: Page.dialog}] event.
   */
  void close(CloseOptions options);
  /**
   * Gets the full HTML contents of the page, including the doctype.
   */
  String content();
  /**
   * Get the browser context that the page belongs to.
   */
  BrowserContext context();
  /**
   * This method double clicks an element matching {@code selector} by performing the following steps:
   * 1. Find an element match matching {@code selector}. If there is none, wait until a matching element is attached to the DOM.
   * 1. Wait for [actionability](./actionability.md) checks on the matched element, unless {@code force} option is set. If the
   *    element is detached during the checks, the whole action is retried.
   * 1. Scroll the element into view if needed.
   * 1. Use [{@code property: Page.mouse}] to double click in the center of the element, or the specified {@code position}.
   * 1. Wait for initiated navigations to either succeed or fail, unless {@code noWaitAfter} option is set. Note that if the
   *    first click of the {@code dblclick()} triggers a navigation event, this method will reject.
   *
   * <p> When all steps combined have not finished during the specified {@code timeout}, this method rejects with a {@code TimeoutError}.
   * Passing zero timeout disables this.
   *
   * <p> <strong>NOTE:</strong> {@code page.dblclick()} dispatches two {@code click} events and a single {@code dblclick} event.
   *
   * <p> Shortcut for main frame's [{@code method: Frame.dblclick}].
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  default void dblclick(String selector) {
    dblclick(selector, null);
  }
  /**
   * This method double clicks an element matching {@code selector} by performing the following steps:
   * 1. Find an element match matching {@code selector}. If there is none, wait until a matching element is attached to the DOM.
   * 1. Wait for [actionability](./actionability.md) checks on the matched element, unless {@code force} option is set. If the
   *    element is detached during the checks, the whole action is retried.
   * 1. Scroll the element into view if needed.
   * 1. Use [{@code property: Page.mouse}] to double click in the center of the element, or the specified {@code position}.
   * 1. Wait for initiated navigations to either succeed or fail, unless {@code noWaitAfter} option is set. Note that if the
   *    first click of the {@code dblclick()} triggers a navigation event, this method will reject.
   *
   * <p> When all steps combined have not finished during the specified {@code timeout}, this method rejects with a {@code TimeoutError}.
   * Passing zero timeout disables this.
   *
   * <p> <strong>NOTE:</strong> {@code page.dblclick()} dispatches two {@code click} events and a single {@code dblclick} event.
   *
   * <p> Shortcut for main frame's [{@code method: Frame.dblclick}].
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  void dblclick(String selector, DblclickOptions options);
  /**
   * The snippet below dispatches the {@code click} event on the element. Regardless of the visibility state of the elment, {@code click}
   * is dispatched. This is equivalend to calling
   * [element.click()](https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/click).
   *
   * <p> Under the hood, it creates an instance of an event based on the given {@code type}, initializes it with {@code eventInit} properties
   * and dispatches it on the element. Events are {@code composed}, {@code cancelable} and bubble by default.
   *
   * <p> Since {@code eventInit} is event-specific, please refer to the events documentation for the lists of initial properties:
   * - [DragEvent](https://developer.mozilla.org/en-US/docs/Web/API/DragEvent/DragEvent)
   * - [FocusEvent](https://developer.mozilla.org/en-US/docs/Web/API/FocusEvent/FocusEvent)
   * - [KeyboardEvent](https://developer.mozilla.org/en-US/docs/Web/API/KeyboardEvent/KeyboardEvent)
   * - [MouseEvent](https://developer.mozilla.org/en-US/docs/Web/API/MouseEvent/MouseEvent)
   * - [PointerEvent](https://developer.mozilla.org/en-US/docs/Web/API/PointerEvent/PointerEvent)
   * - [TouchEvent](https://developer.mozilla.org/en-US/docs/Web/API/TouchEvent/TouchEvent)
   * - [Event](https://developer.mozilla.org/en-US/docs/Web/API/Event/Event)
   *
   * <p> You can also specify {@code JSHandle} as the property value if you want live objects to be passed into the event:
   *
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   * @param type DOM event type: {@code "click"}, {@code "dragstart"}, etc.
   * @param eventInit Optional event-specific initialization properties.
   */
  default void dispatchEvent(String selector, String type, Object eventInit) {
    dispatchEvent(selector, type, eventInit, null);
  }
  /**
   * The snippet below dispatches the {@code click} event on the element. Regardless of the visibility state of the elment, {@code click}
   * is dispatched. This is equivalend to calling
   * [element.click()](https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/click).
   *
   * <p> Under the hood, it creates an instance of an event based on the given {@code type}, initializes it with {@code eventInit} properties
   * and dispatches it on the element. Events are {@code composed}, {@code cancelable} and bubble by default.
   *
   * <p> Since {@code eventInit} is event-specific, please refer to the events documentation for the lists of initial properties:
   * - [DragEvent](https://developer.mozilla.org/en-US/docs/Web/API/DragEvent/DragEvent)
   * - [FocusEvent](https://developer.mozilla.org/en-US/docs/Web/API/FocusEvent/FocusEvent)
   * - [KeyboardEvent](https://developer.mozilla.org/en-US/docs/Web/API/KeyboardEvent/KeyboardEvent)
   * - [MouseEvent](https://developer.mozilla.org/en-US/docs/Web/API/MouseEvent/MouseEvent)
   * - [PointerEvent](https://developer.mozilla.org/en-US/docs/Web/API/PointerEvent/PointerEvent)
   * - [TouchEvent](https://developer.mozilla.org/en-US/docs/Web/API/TouchEvent/TouchEvent)
   * - [Event](https://developer.mozilla.org/en-US/docs/Web/API/Event/Event)
   *
   * <p> You can also specify {@code JSHandle} as the property value if you want live objects to be passed into the event:
   *
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   * @param type DOM event type: {@code "click"}, {@code "dragstart"}, etc.
   */
  default void dispatchEvent(String selector, String type) {
    dispatchEvent(selector, type, null);
  }
  /**
   * The snippet below dispatches the {@code click} event on the element. Regardless of the visibility state of the elment, {@code click}
   * is dispatched. This is equivalend to calling
   * [element.click()](https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/click).
   *
   * <p> Under the hood, it creates an instance of an event based on the given {@code type}, initializes it with {@code eventInit} properties
   * and dispatches it on the element. Events are {@code composed}, {@code cancelable} and bubble by default.
   *
   * <p> Since {@code eventInit} is event-specific, please refer to the events documentation for the lists of initial properties:
   * - [DragEvent](https://developer.mozilla.org/en-US/docs/Web/API/DragEvent/DragEvent)
   * - [FocusEvent](https://developer.mozilla.org/en-US/docs/Web/API/FocusEvent/FocusEvent)
   * - [KeyboardEvent](https://developer.mozilla.org/en-US/docs/Web/API/KeyboardEvent/KeyboardEvent)
   * - [MouseEvent](https://developer.mozilla.org/en-US/docs/Web/API/MouseEvent/MouseEvent)
   * - [PointerEvent](https://developer.mozilla.org/en-US/docs/Web/API/PointerEvent/PointerEvent)
   * - [TouchEvent](https://developer.mozilla.org/en-US/docs/Web/API/TouchEvent/TouchEvent)
   * - [Event](https://developer.mozilla.org/en-US/docs/Web/API/Event/Event)
   *
   * <p> You can also specify {@code JSHandle} as the property value if you want live objects to be passed into the event:
   *
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   * @param type DOM event type: {@code "click"}, {@code "dragstart"}, etc.
   * @param eventInit Optional event-specific initialization properties.
   */
  void dispatchEvent(String selector, String type, Object eventInit, DispatchEventOptions options);
  default void emulateMedia() {
    emulateMedia(null);
  }
  void emulateMedia(EmulateMediaOptions options);
  /**
   * The method finds an element matching the specified selector within the page and passes it as a first argument to
   * {@code expression}. If no elements match the selector, the method throws an error. Returns the value of {@code expression}.
   *
   * <p> If {@code expression} returns a [Promise], then [{@code method: Page.evalOnSelector}] would wait for the promise to resolve and
   * return its value.
   *
   * <p> Shortcut for main frame's [{@code method: Frame.evalOnSelector}].
   *
   * @param selector A selector to query for. See [working with selectors](./selectors.md) for more details.
   * @param expression JavaScript expression to be evaluated in the browser context. If it looks like a function declaration, it is interpreted
   * as a function. Otherwise, evaluated as an expression.
   */
  default Object evalOnSelector(String selector, String expression) {
    return evalOnSelector(selector, expression, null);
  }
  /**
   * The method finds an element matching the specified selector within the page and passes it as a first argument to
   * {@code expression}. If no elements match the selector, the method throws an error. Returns the value of {@code expression}.
   *
   * <p> If {@code expression} returns a [Promise], then [{@code method: Page.evalOnSelector}] would wait for the promise to resolve and
   * return its value.
   *
   * <p> Shortcut for main frame's [{@code method: Frame.evalOnSelector}].
   *
   * @param selector A selector to query for. See [working with selectors](./selectors.md) for more details.
   * @param expression JavaScript expression to be evaluated in the browser context. If it looks like a function declaration, it is interpreted
   * as a function. Otherwise, evaluated as an expression.
   * @param arg Optional argument to pass to {@code expression}.
   */
  Object evalOnSelector(String selector, String expression, Object arg);
  /**
   * The method finds all elements matching the specified selector within the page and passes an array of matched elements as
   * a first argument to {@code expression}. Returns the result of {@code expression} invocation.
   *
   * <p> If {@code expression} returns a [Promise], then [{@code method: Page.evalOnSelectorAll}] would wait for the promise to resolve and
   * return its value.
   *
   *
   * @param selector A selector to query for. See [working with selectors](./selectors.md) for more details.
   * @param expression JavaScript expression to be evaluated in the browser context. If it looks like a function declaration, it is interpreted
   * as a function. Otherwise, evaluated as an expression.
   */
  default Object evalOnSelectorAll(String selector, String expression) {
    return evalOnSelectorAll(selector, expression, null);
  }
  /**
   * The method finds all elements matching the specified selector within the page and passes an array of matched elements as
   * a first argument to {@code expression}. Returns the result of {@code expression} invocation.
   *
   * <p> If {@code expression} returns a [Promise], then [{@code method: Page.evalOnSelectorAll}] would wait for the promise to resolve and
   * return its value.
   *
   *
   * @param selector A selector to query for. See [working with selectors](./selectors.md) for more details.
   * @param expression JavaScript expression to be evaluated in the browser context. If it looks like a function declaration, it is interpreted
   * as a function. Otherwise, evaluated as an expression.
   * @param arg Optional argument to pass to {@code expression}.
   */
  Object evalOnSelectorAll(String selector, String expression, Object arg);
  /**
   * Returns the value of the {@code expression} invocation.
   *
   * <p> If the function passed to the [{@code method: Page.evaluate}] returns a [Promise], then [{@code method: Page.evaluate}] would wait
   * for the promise to resolve and return its value.
   *
   * <p> If the function passed to the [{@code method: Page.evaluate}] returns a non-[Serializable] value, then
   * [{@code method: Page.evaluate}] resolves to {@code undefined}. Playwright also supports transferring some additional values that are
   * not serializable by {@code JSON}: {@code -0}, {@code NaN}, {@code Infinity}, {@code -Infinity}.
   *
   * <p> Passing argument to {@code expression}:
   *
   * <p> A string can also be passed in instead of a function:
   *
   * <p> {@code ElementHandle} instances can be passed as an argument to the [{@code method: Page.evaluate}]:
   *
   * <p> Shortcut for main frame's [{@code method: Frame.evaluate}].
   *
   * @param expression JavaScript expression to be evaluated in the browser context. If it looks like a function declaration, it is interpreted
   * as a function. Otherwise, evaluated as an expression.
   */
  default Object evaluate(String expression) {
    return evaluate(expression, null);
  }
  /**
   * Returns the value of the {@code expression} invocation.
   *
   * <p> If the function passed to the [{@code method: Page.evaluate}] returns a [Promise], then [{@code method: Page.evaluate}] would wait
   * for the promise to resolve and return its value.
   *
   * <p> If the function passed to the [{@code method: Page.evaluate}] returns a non-[Serializable] value, then
   * [{@code method: Page.evaluate}] resolves to {@code undefined}. Playwright also supports transferring some additional values that are
   * not serializable by {@code JSON}: {@code -0}, {@code NaN}, {@code Infinity}, {@code -Infinity}.
   *
   * <p> Passing argument to {@code expression}:
   *
   * <p> A string can also be passed in instead of a function:
   *
   * <p> {@code ElementHandle} instances can be passed as an argument to the [{@code method: Page.evaluate}]:
   *
   * <p> Shortcut for main frame's [{@code method: Frame.evaluate}].
   *
   * @param expression JavaScript expression to be evaluated in the browser context. If it looks like a function declaration, it is interpreted
   * as a function. Otherwise, evaluated as an expression.
   * @param arg Optional argument to pass to {@code expression}.
   */
  Object evaluate(String expression, Object arg);
  /**
   * Returns the value of the {@code expression} invocation as a {@code JSHandle}.
   *
   * <p> The only difference between [{@code method: Page.evaluate}] and [{@code method: Page.evaluateHandle}] is that
   * [{@code method: Page.evaluateHandle}] returns {@code JSHandle}.
   *
   * <p> If the function passed to the [{@code method: Page.evaluateHandle}] returns a [Promise], then [{@code method: Page.evaluateHandle}]
   * would wait for the promise to resolve and return its value.
   *
   * <p> A string can also be passed in instead of a function:
   *
   * <p> {@code JSHandle} instances can be passed as an argument to the [{@code method: Page.evaluateHandle}]:
   *
   *
   * @param expression JavaScript expression to be evaluated in the browser context. If it looks like a function declaration, it is interpreted
   * as a function. Otherwise, evaluated as an expression.
   */
  default JSHandle evaluateHandle(String expression) {
    return evaluateHandle(expression, null);
  }
  /**
   * Returns the value of the {@code expression} invocation as a {@code JSHandle}.
   *
   * <p> The only difference between [{@code method: Page.evaluate}] and [{@code method: Page.evaluateHandle}] is that
   * [{@code method: Page.evaluateHandle}] returns {@code JSHandle}.
   *
   * <p> If the function passed to the [{@code method: Page.evaluateHandle}] returns a [Promise], then [{@code method: Page.evaluateHandle}]
   * would wait for the promise to resolve and return its value.
   *
   * <p> A string can also be passed in instead of a function:
   *
   * <p> {@code JSHandle} instances can be passed as an argument to the [{@code method: Page.evaluateHandle}]:
   *
   *
   * @param expression JavaScript expression to be evaluated in the browser context. If it looks like a function declaration, it is interpreted
   * as a function. Otherwise, evaluated as an expression.
   * @param arg Optional argument to pass to {@code expression}.
   */
  JSHandle evaluateHandle(String expression, Object arg);
  /**
   * The method adds a function called {@code name} on the {@code window} object of every frame in this page. When called, the function
   * executes {@code callback} and returns a [Promise] which resolves to the return value of {@code callback}. If the {@code callback} returns
   * a [Promise], it will be awaited.
   *
   * <p> The first argument of the {@code callback} function contains information about the caller: `{ browserContext: BrowserContext,
   * page: Page, frame: Frame }`.
   *
   * <p> See [{@code method: BrowserContext.exposeBinding}] for the context-wide version.
   *
   * <p> <strong>NOTE:</strong> Functions installed via [{@code method: Page.exposeBinding}] survive navigations.
   *
   *
   * @param name Name of the function on the window object.
   * @param callback Callback function that will be called in the Playwright's context.
   */
  default void exposeBinding(String name, BindingCallback callback) {
    exposeBinding(name, callback, null);
  }
  /**
   * The method adds a function called {@code name} on the {@code window} object of every frame in this page. When called, the function
   * executes {@code callback} and returns a [Promise] which resolves to the return value of {@code callback}. If the {@code callback} returns
   * a [Promise], it will be awaited.
   *
   * <p> The first argument of the {@code callback} function contains information about the caller: `{ browserContext: BrowserContext,
   * page: Page, frame: Frame }`.
   *
   * <p> See [{@code method: BrowserContext.exposeBinding}] for the context-wide version.
   *
   * <p> <strong>NOTE:</strong> Functions installed via [{@code method: Page.exposeBinding}] survive navigations.
   *
   *
   * @param name Name of the function on the window object.
   * @param callback Callback function that will be called in the Playwright's context.
   */
  void exposeBinding(String name, BindingCallback callback, ExposeBindingOptions options);
  /**
   * The method adds a function called {@code name} on the {@code window} object of every frame in the page. When called, the function
   * executes {@code callback} and returns a [Promise] which resolves to the return value of {@code callback}.
   *
   * <p> If the {@code callback} returns a [Promise], it will be awaited.
   *
   * <p> See [{@code method: BrowserContext.exposeFunction}] for context-wide exposed function.
   *
   * <p> <strong>NOTE:</strong> Functions installed via [{@code method: Page.exposeFunction}] survive navigations.
   *
   *
   * @param name Name of the function on the window object
   * @param callback Callback function which will be called in Playwright's context.
   */
  void exposeFunction(String name, FunctionCallback callback);
  /**
   * This method waits for an element matching {@code selector}, waits for [actionability](./actionability.md) checks, focuses the
   * element, fills it and triggers an {@code input} event after filling. If the element is inside the {@code <label>} element that has
   * associated [control](https://developer.mozilla.org/en-US/docs/Web/API/HTMLLabelElement/control), that control will be
   * filled instead. If the element to be filled is not an {@code <input>}, {@code <textarea>} or {@code [contenteditable]} element, this
   * method throws an error. Note that you can pass an empty string to clear the input field.
   *
   * <p> To send fine-grained keyboard events, use [{@code method: Page.type}].
   *
   * <p> Shortcut for main frame's [{@code method: Frame.fill}]
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   * @param value Value to fill for the {@code <input>}, {@code <textarea>} or {@code [contenteditable]} element.
   */
  default void fill(String selector, String value) {
    fill(selector, value, null);
  }
  /**
   * This method waits for an element matching {@code selector}, waits for [actionability](./actionability.md) checks, focuses the
   * element, fills it and triggers an {@code input} event after filling. If the element is inside the {@code <label>} element that has
   * associated [control](https://developer.mozilla.org/en-US/docs/Web/API/HTMLLabelElement/control), that control will be
   * filled instead. If the element to be filled is not an {@code <input>}, {@code <textarea>} or {@code [contenteditable]} element, this
   * method throws an error. Note that you can pass an empty string to clear the input field.
   *
   * <p> To send fine-grained keyboard events, use [{@code method: Page.type}].
   *
   * <p> Shortcut for main frame's [{@code method: Frame.fill}]
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   * @param value Value to fill for the {@code <input>}, {@code <textarea>} or {@code [contenteditable]} element.
   */
  void fill(String selector, String value, FillOptions options);
  /**
   * This method fetches an element with {@code selector} and focuses it. If there's no element matching {@code selector}, the method
   * waits until a matching element appears in the DOM.
   *
   * <p> Shortcut for main frame's [{@code method: Frame.focus}].
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  default void focus(String selector) {
    focus(selector, null);
  }
  /**
   * This method fetches an element with {@code selector} and focuses it. If there's no element matching {@code selector}, the method
   * waits until a matching element appears in the DOM.
   *
   * <p> Shortcut for main frame's [{@code method: Frame.focus}].
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  void focus(String selector, FocusOptions options);
  /**
   * Returns frame matching the specified criteria. Either {@code name} or {@code url} must be specified.
   *
   *
   * @param name Frame name specified in the {@code iframe}'s {@code name} attribute.
   */
  Frame frame(String name);
  /**
   * Returns frame with matching URL.
   *
   * @param url A glob pattern, regex pattern or predicate receiving frame's {@code url} as a [URL] object.
   */
  Frame frameByUrl(String url);
  /**
   * Returns frame with matching URL.
   *
   * @param url A glob pattern, regex pattern or predicate receiving frame's {@code url} as a [URL] object.
   */
  Frame frameByUrl(Pattern url);
  /**
   * Returns frame with matching URL.
   *
   * @param url A glob pattern, regex pattern or predicate receiving frame's {@code url} as a [URL] object.
   */
  Frame frameByUrl(Predicate<String> url);
  /**
   * An array of all frames attached to the page.
   */
  List<Frame> frames();
  /**
   * Returns element attribute value.
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   * @param name Attribute name to get the value for.
   */
  default String getAttribute(String selector, String name) {
    return getAttribute(selector, name, null);
  }
  /**
   * Returns element attribute value.
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   * @param name Attribute name to get the value for.
   */
  String getAttribute(String selector, String name, GetAttributeOptions options);
  /**
   * Returns the main resource response. In case of multiple redirects, the navigation will resolve with the response of the
   * last redirect. If can not go back, returns {@code null}.
   *
   * <p> Navigate to the previous page in history.
   */
  default Response goBack() {
    return goBack(null);
  }
  /**
   * Returns the main resource response. In case of multiple redirects, the navigation will resolve with the response of the
   * last redirect. If can not go back, returns {@code null}.
   *
   * <p> Navigate to the previous page in history.
   */
  Response goBack(GoBackOptions options);
  /**
   * Returns the main resource response. In case of multiple redirects, the navigation will resolve with the response of the
   * last redirect. If can not go forward, returns {@code null}.
   *
   * <p> Navigate to the next page in history.
   */
  default Response goForward() {
    return goForward(null);
  }
  /**
   * Returns the main resource response. In case of multiple redirects, the navigation will resolve with the response of the
   * last redirect. If can not go forward, returns {@code null}.
   *
   * <p> Navigate to the next page in history.
   */
  Response goForward(GoForwardOptions options);
  /**
   * Returns the main resource response. In case of multiple redirects, the navigation will resolve with the response of the
   * last redirect.
   *
   * <p> {@code page.goto} will throw an error if:
   * - there's an SSL error (e.g. in case of self-signed certificates).
   * - target URL is invalid.
   * - the {@code timeout} is exceeded during navigation.
   * - the remote server does not respond or is unreachable.
   * - the main resource failed to load.
   *
   * <p> {@code page.goto} will not throw an error when any valid HTTP status code is returned by the remote server, including 404 "Not
   * Found" and 500 "Internal Server Error".  The status code for such responses can be retrieved by calling
   * [{@code method: Response.status}].
   *
   * <p> <strong>NOTE:</strong> {@code page.goto} either throws an error or returns a main resource response. The only exceptions are navigation to
   * {@code about:blank} or navigation to the same URL with a different hash, which would succeed and return {@code null}.
   * <strong>NOTE:</strong> Headless mode doesn't support navigation to a PDF document. See the
   * [upstream issue](https://bugs.chromium.org/p/chromium/issues/detail?id=761295).
   *
   * <p> Shortcut for main frame's [{@code method: Frame.goto}]
   *
   * @param url URL to navigate page to. The url should include scheme, e.g. {@code https://}.
   */
  default Response navigate(String url) {
    return navigate(url, null);
  }
  /**
   * Returns the main resource response. In case of multiple redirects, the navigation will resolve with the response of the
   * last redirect.
   *
   * <p> {@code page.goto} will throw an error if:
   * - there's an SSL error (e.g. in case of self-signed certificates).
   * - target URL is invalid.
   * - the {@code timeout} is exceeded during navigation.
   * - the remote server does not respond or is unreachable.
   * - the main resource failed to load.
   *
   * <p> {@code page.goto} will not throw an error when any valid HTTP status code is returned by the remote server, including 404 "Not
   * Found" and 500 "Internal Server Error".  The status code for such responses can be retrieved by calling
   * [{@code method: Response.status}].
   *
   * <p> <strong>NOTE:</strong> {@code page.goto} either throws an error or returns a main resource response. The only exceptions are navigation to
   * {@code about:blank} or navigation to the same URL with a different hash, which would succeed and return {@code null}.
   * <strong>NOTE:</strong> Headless mode doesn't support navigation to a PDF document. See the
   * [upstream issue](https://bugs.chromium.org/p/chromium/issues/detail?id=761295).
   *
   * <p> Shortcut for main frame's [{@code method: Frame.goto}]
   *
   * @param url URL to navigate page to. The url should include scheme, e.g. {@code https://}.
   */
  Response navigate(String url, NavigateOptions options);
  /**
   * This method hovers over an element matching {@code selector} by performing the following steps:
   * 1. Find an element match matching {@code selector}. If there is none, wait until a matching element is attached to the DOM.
   * 1. Wait for [actionability](./actionability.md) checks on the matched element, unless {@code force} option is set. If the
   *    element is detached during the checks, the whole action is retried.
   * 1. Scroll the element into view if needed.
   * 1. Use [{@code property: Page.mouse}] to hover over the center of the element, or the specified {@code position}.
   * 1. Wait for initiated navigations to either succeed or fail, unless {@code noWaitAfter} option is set.
   *
   * <p> When all steps combined have not finished during the specified {@code timeout}, this method rejects with a {@code TimeoutError}.
   * Passing zero timeout disables this.
   *
   * <p> Shortcut for main frame's [{@code method: Frame.hover}].
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  default void hover(String selector) {
    hover(selector, null);
  }
  /**
   * This method hovers over an element matching {@code selector} by performing the following steps:
   * 1. Find an element match matching {@code selector}. If there is none, wait until a matching element is attached to the DOM.
   * 1. Wait for [actionability](./actionability.md) checks on the matched element, unless {@code force} option is set. If the
   *    element is detached during the checks, the whole action is retried.
   * 1. Scroll the element into view if needed.
   * 1. Use [{@code property: Page.mouse}] to hover over the center of the element, or the specified {@code position}.
   * 1. Wait for initiated navigations to either succeed or fail, unless {@code noWaitAfter} option is set.
   *
   * <p> When all steps combined have not finished during the specified {@code timeout}, this method rejects with a {@code TimeoutError}.
   * Passing zero timeout disables this.
   *
   * <p> Shortcut for main frame's [{@code method: Frame.hover}].
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  void hover(String selector, HoverOptions options);
  /**
   * Returns {@code element.innerHTML}.
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  default String innerHTML(String selector) {
    return innerHTML(selector, null);
  }
  /**
   * Returns {@code element.innerHTML}.
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  String innerHTML(String selector, InnerHTMLOptions options);
  /**
   * Returns {@code element.innerText}.
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  default String innerText(String selector) {
    return innerText(selector, null);
  }
  /**
   * Returns {@code element.innerText}.
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  String innerText(String selector, InnerTextOptions options);
  /**
   * Returns whether the element is checked. Throws if the element is not a checkbox or radio input.
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  default boolean isChecked(String selector) {
    return isChecked(selector, null);
  }
  /**
   * Returns whether the element is checked. Throws if the element is not a checkbox or radio input.
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  boolean isChecked(String selector, IsCheckedOptions options);
  /**
   * Indicates that the page has been closed.
   */
  boolean isClosed();
  /**
   * Returns whether the element is disabled, the opposite of [enabled](./actionability.md#enabled).
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  default boolean isDisabled(String selector) {
    return isDisabled(selector, null);
  }
  /**
   * Returns whether the element is disabled, the opposite of [enabled](./actionability.md#enabled).
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  boolean isDisabled(String selector, IsDisabledOptions options);
  /**
   * Returns whether the element is [editable](./actionability.md#editable).
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  default boolean isEditable(String selector) {
    return isEditable(selector, null);
  }
  /**
   * Returns whether the element is [editable](./actionability.md#editable).
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  boolean isEditable(String selector, IsEditableOptions options);
  /**
   * Returns whether the element is [enabled](./actionability.md#enabled).
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  default boolean isEnabled(String selector) {
    return isEnabled(selector, null);
  }
  /**
   * Returns whether the element is [enabled](./actionability.md#enabled).
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  boolean isEnabled(String selector, IsEnabledOptions options);
  /**
   * Returns whether the element is hidden, the opposite of [visible](./actionability.md#visible).  {@code selector} that does not
   * match any elements is considered hidden.
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  default boolean isHidden(String selector) {
    return isHidden(selector, null);
  }
  /**
   * Returns whether the element is hidden, the opposite of [visible](./actionability.md#visible).  {@code selector} that does not
   * match any elements is considered hidden.
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  boolean isHidden(String selector, IsHiddenOptions options);
  /**
   * Returns whether the element is [visible](./actionability.md#visible). {@code selector} that does not match any elements is
   * considered not visible.
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  default boolean isVisible(String selector) {
    return isVisible(selector, null);
  }
  /**
   * Returns whether the element is [visible](./actionability.md#visible). {@code selector} that does not match any elements is
   * considered not visible.
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  boolean isVisible(String selector, IsVisibleOptions options);
  Keyboard keyboard();
  /**
   * The page's main frame. Page is guaranteed to have a main frame which persists during navigations.
   */
  Frame mainFrame();
  Mouse mouse();
  /**
   * Returns the opener for popup pages and {@code null} for others. If the opener has been closed already the returns {@code null}.
   */
  Page opener();
  /**
   * Pauses script execution. Playwright will stop executing the script and wait for the user to either press 'Resume' button
   * in the page overlay or to call {@code playwright.resume()} in the DevTools console.
   *
   * <p> User can inspect selectors or perform manual steps while paused. Resume will continue running the original script from
   * the place it was paused.
   *
   * <p> <strong>NOTE:</strong> This method requires Playwright to be started in a headed mode, with a falsy {@code headless} value in the
   * [{@code method: BrowserType.launch}].
   */
  void pause();
  /**
   * Returns the PDF buffer.
   *
   * <p> <strong>NOTE:</strong> Generating a pdf is currently only supported in Chromium headless.
   *
   * <p> {@code page.pdf()} generates a pdf of the page with {@code print} css media. To generate a pdf with {@code screen} media, call
   * [{@code method: Page.emulateMedia}] before calling {@code page.pdf()}:
   *
   * <p> <strong>NOTE:</strong> By default, {@code page.pdf()} generates a pdf with modified colors for printing. Use the
   * [{@code -webkit-print-color-adjust}](https://developer.mozilla.org/en-US/docs/Web/CSS/-webkit-print-color-adjust) property to
   * force rendering of exact colors.
   *
   * <p> The {@code width}, {@code height}, and {@code margin} options accept values labeled with units. Unlabeled values are treated as pixels.
   *
   * <p> A few examples:
   * - {@code page.pdf({width: 100})} - prints with width set to 100 pixels
   * - {@code page.pdf({width: '100px'})} - prints with width set to 100 pixels
   * - {@code page.pdf({width: '10cm'})} - prints with width set to 10 centimeters.
   *
   * <p> All possible units are:
   * - {@code px} - pixel
   * - {@code in} - inch
   * - {@code cm} - centimeter
   * - {@code mm} - millimeter
   *
   * <p> The {@code format} options are:
   * - {@code Letter}: 8.5in x 11in
   * - {@code Legal}: 8.5in x 14in
   * - {@code Tabloid}: 11in x 17in
   * - {@code Ledger}: 17in x 11in
   * - {@code A0}: 33.1in x 46.8in
   * - {@code A1}: 23.4in x 33.1in
   * - {@code A2}: 16.54in x 23.4in
   * - {@code A3}: 11.7in x 16.54in
   * - {@code A4}: 8.27in x 11.7in
   * - {@code A5}: 5.83in x 8.27in
   * - {@code A6}: 4.13in x 5.83in
   *
   * <p> <strong>NOTE:</strong> {@code headerTemplate} and {@code footerTemplate} markup have the following limitations: > 1. Script tags inside templates
   * are not evaluated. > 2. Page styles are not visible inside templates.
   */
  default byte[] pdf() {
    return pdf(null);
  }
  /**
   * Returns the PDF buffer.
   *
   * <p> <strong>NOTE:</strong> Generating a pdf is currently only supported in Chromium headless.
   *
   * <p> {@code page.pdf()} generates a pdf of the page with {@code print} css media. To generate a pdf with {@code screen} media, call
   * [{@code method: Page.emulateMedia}] before calling {@code page.pdf()}:
   *
   * <p> <strong>NOTE:</strong> By default, {@code page.pdf()} generates a pdf with modified colors for printing. Use the
   * [{@code -webkit-print-color-adjust}](https://developer.mozilla.org/en-US/docs/Web/CSS/-webkit-print-color-adjust) property to
   * force rendering of exact colors.
   *
   * <p> The {@code width}, {@code height}, and {@code margin} options accept values labeled with units. Unlabeled values are treated as pixels.
   *
   * <p> A few examples:
   * - {@code page.pdf({width: 100})} - prints with width set to 100 pixels
   * - {@code page.pdf({width: '100px'})} - prints with width set to 100 pixels
   * - {@code page.pdf({width: '10cm'})} - prints with width set to 10 centimeters.
   *
   * <p> All possible units are:
   * - {@code px} - pixel
   * - {@code in} - inch
   * - {@code cm} - centimeter
   * - {@code mm} - millimeter
   *
   * <p> The {@code format} options are:
   * - {@code Letter}: 8.5in x 11in
   * - {@code Legal}: 8.5in x 14in
   * - {@code Tabloid}: 11in x 17in
   * - {@code Ledger}: 17in x 11in
   * - {@code A0}: 33.1in x 46.8in
   * - {@code A1}: 23.4in x 33.1in
   * - {@code A2}: 16.54in x 23.4in
   * - {@code A3}: 11.7in x 16.54in
   * - {@code A4}: 8.27in x 11.7in
   * - {@code A5}: 5.83in x 8.27in
   * - {@code A6}: 4.13in x 5.83in
   *
   * <p> <strong>NOTE:</strong> {@code headerTemplate} and {@code footerTemplate} markup have the following limitations: > 1. Script tags inside templates
   * are not evaluated. > 2. Page styles are not visible inside templates.
   */
  byte[] pdf(PdfOptions options);
  /**
   * Focuses the element, and then uses [{@code method: Keyboard.down}] and [{@code method: Keyboard.up}].
   *
   * <p> {@code key} can specify the intended [keyboardEvent.key](https://developer.mozilla.org/en-US/docs/Web/API/KeyboardEvent/key)
   * value or a single character to generate the text for. A superset of the {@code key} values can be found
   * [here](https://developer.mozilla.org/en-US/docs/Web/API/KeyboardEvent/key/Key_Values). Examples of the keys are:
   *
   * <p> {@code F1} - {@code F12}, {@code Digit0}- {@code Digit9}, {@code KeyA}- {@code KeyZ}, {@code Backquote}, {@code Minus}, {@code Equal}, {@code Backslash}, {@code Backspace}, {@code Tab},
   * {@code Delete}, {@code Escape}, {@code ArrowDown}, {@code End}, {@code Enter}, {@code Home}, {@code Insert}, {@code PageDown}, {@code PageUp}, {@code ArrowRight}, {@code ArrowUp}, etc.
   *
   * <p> Following modification shortcuts are also supported: {@code Shift}, {@code Control}, {@code Alt}, {@code Meta}, {@code ShiftLeft}.
   *
   * <p> Holding down {@code Shift} will type the text that corresponds to the {@code key} in the upper case.
   *
   * <p> If {@code key} is a single character, it is case-sensitive, so the values {@code a} and {@code A} will generate different respective
   * texts.
   *
   * <p> Shortcuts such as {@code key: "Control+o"} or {@code key: "Control+Shift+T"} are supported as well. When speficied with the
   * modifier, modifier is pressed and being held while the subsequent key is being pressed.
   *
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   * @param key Name of the key to press or a character to generate, such as {@code ArrowLeft} or {@code a}.
   */
  default void press(String selector, String key) {
    press(selector, key, null);
  }
  /**
   * Focuses the element, and then uses [{@code method: Keyboard.down}] and [{@code method: Keyboard.up}].
   *
   * <p> {@code key} can specify the intended [keyboardEvent.key](https://developer.mozilla.org/en-US/docs/Web/API/KeyboardEvent/key)
   * value or a single character to generate the text for. A superset of the {@code key} values can be found
   * [here](https://developer.mozilla.org/en-US/docs/Web/API/KeyboardEvent/key/Key_Values). Examples of the keys are:
   *
   * <p> {@code F1} - {@code F12}, {@code Digit0}- {@code Digit9}, {@code KeyA}- {@code KeyZ}, {@code Backquote}, {@code Minus}, {@code Equal}, {@code Backslash}, {@code Backspace}, {@code Tab},
   * {@code Delete}, {@code Escape}, {@code ArrowDown}, {@code End}, {@code Enter}, {@code Home}, {@code Insert}, {@code PageDown}, {@code PageUp}, {@code ArrowRight}, {@code ArrowUp}, etc.
   *
   * <p> Following modification shortcuts are also supported: {@code Shift}, {@code Control}, {@code Alt}, {@code Meta}, {@code ShiftLeft}.
   *
   * <p> Holding down {@code Shift} will type the text that corresponds to the {@code key} in the upper case.
   *
   * <p> If {@code key} is a single character, it is case-sensitive, so the values {@code a} and {@code A} will generate different respective
   * texts.
   *
   * <p> Shortcuts such as {@code key: "Control+o"} or {@code key: "Control+Shift+T"} are supported as well. When speficied with the
   * modifier, modifier is pressed and being held while the subsequent key is being pressed.
   *
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   * @param key Name of the key to press or a character to generate, such as {@code ArrowLeft} or {@code a}.
   */
  void press(String selector, String key, PressOptions options);
  /**
   * The method finds an element matching the specified selector within the page. If no elements match the selector, the
   * return value resolves to {@code null}.
   *
   * <p> Shortcut for main frame's [{@code method: Frame.querySelector}].
   *
   * @param selector A selector to query for. See [working with selectors](./selectors.md) for more details.
   */
  ElementHandle querySelector(String selector);
  /**
   * The method finds all elements matching the specified selector within the page. If no elements match the selector, the
   * return value resolves to {@code []}.
   *
   * <p> Shortcut for main frame's [{@code method: Frame.querySelectorAll}].
   *
   * @param selector A selector to query for. See [working with selectors](./selectors.md) for more details.
   */
  List<ElementHandle> querySelectorAll(String selector);
  /**
   * Returns the main resource response. In case of multiple redirects, the navigation will resolve with the response of the
   * last redirect.
   */
  default Response reload() {
    return reload(null);
  }
  /**
   * Returns the main resource response. In case of multiple redirects, the navigation will resolve with the response of the
   * last redirect.
   */
  Response reload(ReloadOptions options);
  /**
   * Routing provides the capability to modify network requests that are made by a page.
   *
   * <p> Once routing is enabled, every request matching the url pattern will stall unless it's continued, fulfilled or aborted.
   *
   * <p> <strong>NOTE:</strong> The handler will only be called for the first url if the response is a redirect.
   *
   * <p> or the same snippet using a regex pattern instead:
   *
   * <p> Page routes take precedence over browser context routes (set up with [{@code method: BrowserContext.route}]) when request
   * matches both handlers.
   *
   * <p> <strong>NOTE:</strong> Enabling routing disables http cache.
   *
   * @param url A glob pattern, regex pattern or predicate receiving [URL] to match while routing.
   * @param handler handler function to route the request.
   */
  void route(String url, Consumer<Route> handler);
  /**
   * Routing provides the capability to modify network requests that are made by a page.
   *
   * <p> Once routing is enabled, every request matching the url pattern will stall unless it's continued, fulfilled or aborted.
   *
   * <p> <strong>NOTE:</strong> The handler will only be called for the first url if the response is a redirect.
   *
   * <p> or the same snippet using a regex pattern instead:
   *
   * <p> Page routes take precedence over browser context routes (set up with [{@code method: BrowserContext.route}]) when request
   * matches both handlers.
   *
   * <p> <strong>NOTE:</strong> Enabling routing disables http cache.
   *
   * @param url A glob pattern, regex pattern or predicate receiving [URL] to match while routing.
   * @param handler handler function to route the request.
   */
  void route(Pattern url, Consumer<Route> handler);
  /**
   * Routing provides the capability to modify network requests that are made by a page.
   *
   * <p> Once routing is enabled, every request matching the url pattern will stall unless it's continued, fulfilled or aborted.
   *
   * <p> <strong>NOTE:</strong> The handler will only be called for the first url if the response is a redirect.
   *
   * <p> or the same snippet using a regex pattern instead:
   *
   * <p> Page routes take precedence over browser context routes (set up with [{@code method: BrowserContext.route}]) when request
   * matches both handlers.
   *
   * <p> <strong>NOTE:</strong> Enabling routing disables http cache.
   *
   * @param url A glob pattern, regex pattern or predicate receiving [URL] to match while routing.
   * @param handler handler function to route the request.
   */
  void route(Predicate<String> url, Consumer<Route> handler);
  /**
   * Returns the buffer with the captured screenshot.
   *
   * <p> <strong>NOTE:</strong> Screenshots take at least 1/6 second on Chromium OS X and Chromium Windows. See https://crbug.com/741689 for
   * discussion.
   */
  default byte[] screenshot() {
    return screenshot(null);
  }
  /**
   * Returns the buffer with the captured screenshot.
   *
   * <p> <strong>NOTE:</strong> Screenshots take at least 1/6 second on Chromium OS X and Chromium Windows. See https://crbug.com/741689 for
   * discussion.
   */
  byte[] screenshot(ScreenshotOptions options);
  default List<String> selectOption(String selector, String value) {
    return selectOption(selector, value, null);
  }
  default List<String> selectOption(String selector, String value, SelectOptionOptions options) {
    String[] values = value == null ? null : new String[]{ value };
    return selectOption(selector, values, options);
  }
  default List<String> selectOption(String selector, String[] values) {
    return selectOption(selector, values, null);
  }
  default List<String> selectOption(String selector, String[] values, SelectOptionOptions options) {
    if (values == null) {
      return selectOption(selector, new ElementHandle.SelectOption[0], options);
    }
    return selectOption(selector, Arrays.asList(values).stream().map(
      v -> new ElementHandle.SelectOption().withValue(v)).toArray(ElementHandle.SelectOption[]::new), options);
  }
  default List<String> selectOption(String selector, ElementHandle.SelectOption value) {
    return selectOption(selector, value, null);
  }
  default List<String> selectOption(String selector, ElementHandle.SelectOption value, SelectOptionOptions options) {
    ElementHandle.SelectOption[] values = value == null ? null : new ElementHandle.SelectOption[]{value};
    return selectOption(selector, values, options);
  }
  default List<String> selectOption(String selector, ElementHandle.SelectOption[] values) {
    return selectOption(selector, values, null);
  }
  List<String> selectOption(String selector, ElementHandle.SelectOption[] values, SelectOptionOptions options);
  default List<String> selectOption(String selector, ElementHandle value) {
    return selectOption(selector, value, null);
  }
  default List<String> selectOption(String selector, ElementHandle value, SelectOptionOptions options) {
    ElementHandle[] values = value == null ? null : new ElementHandle[]{value};
    return selectOption(selector, values, options);
  }
  default List<String> selectOption(String selector, ElementHandle[] values) {
    return selectOption(selector, values, null);
  }
  /**
   * Returns the array of option values that have been successfully selected.
   *
   * <p> Triggers a {@code change} and {@code input} event once all the provided options have been selected. If there's no {@code <select>} element
   * matching {@code selector}, the method throws an error.
   *
   * <p> Will wait until all specified options are present in the {@code <select>} element.
   *
   * <p> Shortcut for main frame's [{@code method: Frame.selectOption}]
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   * @param values Options to select. If the {@code <select>} has the {@code multiple} attribute, all matching options are selected, otherwise only the
   * first option matching one of the passed options is selected. String values are equivalent to {@code {value:'string'}}. Option
   * is considered matching if all specified properties match.
   */
  List<String> selectOption(String selector, ElementHandle[] values, SelectOptionOptions options);
  /**
   *
   *
   * @param html HTML markup to assign to the page.
   */
  default void setContent(String html) {
    setContent(html, null);
  }
  /**
   *
   *
   * @param html HTML markup to assign to the page.
   */
  void setContent(String html, SetContentOptions options);
  /**
   * This setting will change the default maximum navigation time for the following methods and related shortcuts:
   * - [{@code method: Page.goBack}]
   * - [{@code method: Page.goForward}]
   * - [{@code method: Page.goto}]
   * - [{@code method: Page.reload}]
   * - [{@code method: Page.setContent}]
   * - [{@code method: Page.waitForNavigation}]
   *
   * <p> <strong>NOTE:</strong> [{@code method: Page.setDefaultNavigationTimeout}] takes priority over [{@code method: Page.setDefaultTimeout}],
   * [{@code method: BrowserContext.setDefaultTimeout}] and [{@code method: BrowserContext.setDefaultNavigationTimeout}].
   *
   * @param timeout Maximum navigation time in milliseconds
   */
  void setDefaultNavigationTimeout(double timeout);
  /**
   * This setting will change the default maximum time for all the methods accepting {@code timeout} option.
   *
   * <p> <strong>NOTE:</strong> [{@code method: Page.setDefaultNavigationTimeout}] takes priority over [{@code method: Page.setDefaultTimeout}].
   *
   * @param timeout Maximum time in milliseconds
   */
  void setDefaultTimeout(double timeout);
  /**
   * The extra HTTP headers will be sent with every request the page initiates.
   *
   * <p> <strong>NOTE:</strong> [{@code method: Page.setExtraHTTPHeaders}] does not guarantee the order of headers in the outgoing requests.
   *
   * @param headers An object containing additional HTTP headers to be sent with every request. All header values must be strings.
   */
  void setExtraHTTPHeaders(Map<String, String> headers);
  /**
   * This method expects {@code selector} to point to an
   * [input element](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input).
   *
   * <p> Sets the value of the file input to these file paths or files. If some of the {@code filePaths} are relative paths, then they
   * are resolved relative to the the current working directory. For empty array, clears the selected files.
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  default void setInputFiles(String selector, Path files) {
    setInputFiles(selector, files, null);
  }
  /**
   * This method expects {@code selector} to point to an
   * [input element](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input).
   *
   * <p> Sets the value of the file input to these file paths or files. If some of the {@code filePaths} are relative paths, then they
   * are resolved relative to the the current working directory. For empty array, clears the selected files.
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  void setInputFiles(String selector, Path files, SetInputFilesOptions options);
  /**
   * This method expects {@code selector} to point to an
   * [input element](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input).
   *
   * <p> Sets the value of the file input to these file paths or files. If some of the {@code filePaths} are relative paths, then they
   * are resolved relative to the the current working directory. For empty array, clears the selected files.
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  default void setInputFiles(String selector, Path[] files) {
    setInputFiles(selector, files, null);
  }
  /**
   * This method expects {@code selector} to point to an
   * [input element](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input).
   *
   * <p> Sets the value of the file input to these file paths or files. If some of the {@code filePaths} are relative paths, then they
   * are resolved relative to the the current working directory. For empty array, clears the selected files.
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  void setInputFiles(String selector, Path[] files, SetInputFilesOptions options);
  /**
   * This method expects {@code selector} to point to an
   * [input element](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input).
   *
   * <p> Sets the value of the file input to these file paths or files. If some of the {@code filePaths} are relative paths, then they
   * are resolved relative to the the current working directory. For empty array, clears the selected files.
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  default void setInputFiles(String selector, FilePayload files) {
    setInputFiles(selector, files, null);
  }
  /**
   * This method expects {@code selector} to point to an
   * [input element](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input).
   *
   * <p> Sets the value of the file input to these file paths or files. If some of the {@code filePaths} are relative paths, then they
   * are resolved relative to the the current working directory. For empty array, clears the selected files.
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  void setInputFiles(String selector, FilePayload files, SetInputFilesOptions options);
  /**
   * This method expects {@code selector} to point to an
   * [input element](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input).
   *
   * <p> Sets the value of the file input to these file paths or files. If some of the {@code filePaths} are relative paths, then they
   * are resolved relative to the the current working directory. For empty array, clears the selected files.
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  default void setInputFiles(String selector, FilePayload[] files) {
    setInputFiles(selector, files, null);
  }
  /**
   * This method expects {@code selector} to point to an
   * [input element](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input).
   *
   * <p> Sets the value of the file input to these file paths or files. If some of the {@code filePaths} are relative paths, then they
   * are resolved relative to the the current working directory. For empty array, clears the selected files.
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  void setInputFiles(String selector, FilePayload[] files, SetInputFilesOptions options);
  /**
   * In the case of multiple pages in a single browser, each page can have its own viewport size. However,
   * [{@code method: Browser.newContext}] allows to set viewport size (and more) for all pages in the context at once.
   *
   * <p> {@code page.setViewportSize} will resize the page. A lot of websites don't expect phones to change size, so you should set the
   * viewport size before navigating to the page.
   */
  void setViewportSize(int width, int height);
  /**
   * This method taps an element matching {@code selector} by performing the following steps:
   * 1. Find an element match matching {@code selector}. If there is none, wait until a matching element is attached to the DOM.
   * 1. Wait for [actionability](./actionability.md) checks on the matched element, unless {@code force} option is set. If the
   *    element is detached during the checks, the whole action is retried.
   * 1. Scroll the element into view if needed.
   * 1. Use [{@code property: Page.touchscreen}] to tap the center of the element, or the specified {@code position}.
   * 1. Wait for initiated navigations to either succeed or fail, unless {@code noWaitAfter} option is set.
   *
   * <p> When all steps combined have not finished during the specified {@code timeout}, this method rejects with a {@code TimeoutError}.
   * Passing zero timeout disables this.
   *
   * <p> <strong>NOTE:</strong> [{@code method: Page.tap}] requires that the {@code hasTouch} option of the browser context be set to true.
   *
   * <p> Shortcut for main frame's [{@code method: Frame.tap}].
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  default void tap(String selector) {
    tap(selector, null);
  }
  /**
   * This method taps an element matching {@code selector} by performing the following steps:
   * 1. Find an element match matching {@code selector}. If there is none, wait until a matching element is attached to the DOM.
   * 1. Wait for [actionability](./actionability.md) checks on the matched element, unless {@code force} option is set. If the
   *    element is detached during the checks, the whole action is retried.
   * 1. Scroll the element into view if needed.
   * 1. Use [{@code property: Page.touchscreen}] to tap the center of the element, or the specified {@code position}.
   * 1. Wait for initiated navigations to either succeed or fail, unless {@code noWaitAfter} option is set.
   *
   * <p> When all steps combined have not finished during the specified {@code timeout}, this method rejects with a {@code TimeoutError}.
   * Passing zero timeout disables this.
   *
   * <p> <strong>NOTE:</strong> [{@code method: Page.tap}] requires that the {@code hasTouch} option of the browser context be set to true.
   *
   * <p> Shortcut for main frame's [{@code method: Frame.tap}].
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  void tap(String selector, TapOptions options);
  /**
   * Returns {@code element.textContent}.
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  default String textContent(String selector) {
    return textContent(selector, null);
  }
  /**
   * Returns {@code element.textContent}.
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  String textContent(String selector, TextContentOptions options);
  /**
   * Returns the page's title. Shortcut for main frame's [{@code method: Frame.title}].
   */
  String title();
  Touchscreen touchscreen();
  /**
   * Sends a {@code keydown}, {@code keypress}/{@code input}, and {@code keyup} event for each character in the text. {@code page.type} can be used to send
   * fine-grained keyboard events. To fill values in form fields, use [{@code method: Page.fill}].
   *
   * <p> To press a special key, like {@code Control} or {@code ArrowDown}, use [{@code method: Keyboard.press}].
   *
   * <p> Shortcut for main frame's [{@code method: Frame.type}].
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   * @param text A text to type into a focused element.
   */
  default void type(String selector, String text) {
    type(selector, text, null);
  }
  /**
   * Sends a {@code keydown}, {@code keypress}/{@code input}, and {@code keyup} event for each character in the text. {@code page.type} can be used to send
   * fine-grained keyboard events. To fill values in form fields, use [{@code method: Page.fill}].
   *
   * <p> To press a special key, like {@code Control} or {@code ArrowDown}, use [{@code method: Keyboard.press}].
   *
   * <p> Shortcut for main frame's [{@code method: Frame.type}].
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   * @param text A text to type into a focused element.
   */
  void type(String selector, String text, TypeOptions options);
  /**
   * This method unchecks an element matching {@code selector} by performing the following steps:
   * 1. Find an element match matching {@code selector}. If there is none, wait until a matching element is attached to the DOM.
   * 1. Ensure that matched element is a checkbox or a radio input. If not, this method rejects. If the element is already
   *    unchecked, this method returns immediately.
   * 1. Wait for [actionability](./actionability.md) checks on the matched element, unless {@code force} option is set. If the
   *    element is detached during the checks, the whole action is retried.
   * 1. Scroll the element into view if needed.
   * 1. Use [{@code property: Page.mouse}] to click in the center of the element.
   * 1. Wait for initiated navigations to either succeed or fail, unless {@code noWaitAfter} option is set.
   * 1. Ensure that the element is now unchecked. If not, this method rejects.
   *
   * <p> When all steps combined have not finished during the specified {@code timeout}, this method rejects with a {@code TimeoutError}.
   * Passing zero timeout disables this.
   *
   * <p> Shortcut for main frame's [{@code method: Frame.uncheck}].
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  default void uncheck(String selector) {
    uncheck(selector, null);
  }
  /**
   * This method unchecks an element matching {@code selector} by performing the following steps:
   * 1. Find an element match matching {@code selector}. If there is none, wait until a matching element is attached to the DOM.
   * 1. Ensure that matched element is a checkbox or a radio input. If not, this method rejects. If the element is already
   *    unchecked, this method returns immediately.
   * 1. Wait for [actionability](./actionability.md) checks on the matched element, unless {@code force} option is set. If the
   *    element is detached during the checks, the whole action is retried.
   * 1. Scroll the element into view if needed.
   * 1. Use [{@code property: Page.mouse}] to click in the center of the element.
   * 1. Wait for initiated navigations to either succeed or fail, unless {@code noWaitAfter} option is set.
   * 1. Ensure that the element is now unchecked. If not, this method rejects.
   *
   * <p> When all steps combined have not finished during the specified {@code timeout}, this method rejects with a {@code TimeoutError}.
   * Passing zero timeout disables this.
   *
   * <p> Shortcut for main frame's [{@code method: Frame.uncheck}].
   *
   * @param selector A selector to search for element. If there are multiple elements satisfying the selector, the first will be used. See
   * [working with selectors](./selectors.md) for more details.
   */
  void uncheck(String selector, UncheckOptions options);
  /**
   * Removes a route created with [{@code method: Page.route}]. When {@code handler} is not specified, removes all routes for the {@code url}.
   *
   * @param url A glob pattern, regex pattern or predicate receiving [URL] to match while routing.
   */
  default void unroute(String url) {
    unroute(url, null);
  }
  /**
   * Removes a route created with [{@code method: Page.route}]. When {@code handler} is not specified, removes all routes for the {@code url}.
   *
   * @param url A glob pattern, regex pattern or predicate receiving [URL] to match while routing.
   * @param handler Optional handler function to route the request.
   */
  void unroute(String url, Consumer<Route> handler);
  /**
   * Removes a route created with [{@code method: Page.route}]. When {@code handler} is not specified, removes all routes for the {@code url}.
   *
   * @param url A glob pattern, regex pattern or predicate receiving [URL] to match while routing.
   */
  default void unroute(Pattern url) {
    unroute(url, null);
  }
  /**
   * Removes a route created with [{@code method: Page.route}]. When {@code handler} is not specified, removes all routes for the {@code url}.
   *
   * @param url A glob pattern, regex pattern or predicate receiving [URL] to match while routing.
   * @param handler Optional handler function to route the request.
   */
  void unroute(Pattern url, Consumer<Route> handler);
  /**
   * Removes a route created with [{@code method: Page.route}]. When {@code handler} is not specified, removes all routes for the {@code url}.
   *
   * @param url A glob pattern, regex pattern or predicate receiving [URL] to match while routing.
   */
  default void unroute(Predicate<String> url) {
    unroute(url, null);
  }
  /**
   * Removes a route created with [{@code method: Page.route}]. When {@code handler} is not specified, removes all routes for the {@code url}.
   *
   * @param url A glob pattern, regex pattern or predicate receiving [URL] to match while routing.
   * @param handler Optional handler function to route the request.
   */
  void unroute(Predicate<String> url, Consumer<Route> handler);
  /**
   * Shortcut for main frame's [{@code method: Frame.url}].
   */
  String url();
  /**
   * Video object associated with this page.
   */
  Video video();
  ViewportSize viewportSize();
  /**
   * Performs action and waits for the Page to close.
   *
   * @param callback Callback that performs the action triggering the event.
   */
  default Page waitForClose(Runnable callback) {
    return waitForClose(null, callback);
  }
  /**
   * Performs action and waits for the Page to close.
   *
   * @param callback Callback that performs the action triggering the event.
   */
  Page waitForClose(WaitForCloseOptions options, Runnable callback);
  /**
   * Performs action and waits for a [ConoleMessage] to be logged by in the page. If predicate is provided, it passes
   * {@code ConsoleMessage} value into the {@code predicate} function and waits for {@code predicate(message)} to return a truthy value. Will
   * throw an error if the page is closed before the console event is fired.
   *
   * @param callback Callback that performs the action triggering the event.
   */
  default ConsoleMessage waitForConsoleMessage(Runnable callback) {
    return waitForConsoleMessage(null, callback);
  }
  /**
   * Performs action and waits for a [ConoleMessage] to be logged by in the page. If predicate is provided, it passes
   * {@code ConsoleMessage} value into the {@code predicate} function and waits for {@code predicate(message)} to return a truthy value. Will
   * throw an error if the page is closed before the console event is fired.
   *
   * @param callback Callback that performs the action triggering the event.
   */
  ConsoleMessage waitForConsoleMessage(WaitForConsoleMessageOptions options, Runnable callback);
  /**
   * Performs action and waits for a new {@code Download}. If predicate is provided, it passes {@code Download} value into the
   * {@code predicate} function and waits for {@code predicate(download)} to return a truthy value. Will throw an error if the page is
   * closed before the download event is fired.
   *
   * @param callback Callback that performs the action triggering the event.
   */
  default Download waitForDownload(Runnable callback) {
    return waitForDownload(null, callback);
  }
  /**
   * Performs action and waits for a new {@code Download}. If predicate is provided, it passes {@code Download} value into the
   * {@code predicate} function and waits for {@code predicate(download)} to return a truthy value. Will throw an error if the page is
   * closed before the download event is fired.
   *
   * @param callback Callback that performs the action triggering the event.
   */
  Download waitForDownload(WaitForDownloadOptions options, Runnable callback);
  /**
   * Performs action and waits for a new {@code FileChooser} to be created. If predicate is provided, it passes {@code FileChooser} value
   * into the {@code predicate} function and waits for {@code predicate(fileChooser)} to return a truthy value. Will throw an error if
   * the page is closed before the file chooser is opened.
   *
   * @param callback Callback that performs the action triggering the event.
   */
  default FileChooser waitForFileChooser(Runnable callback) {
    return waitForFileChooser(null, callback);
  }
  /**
   * Performs action and waits for a new {@code FileChooser} to be created. If predicate is provided, it passes {@code FileChooser} value
   * into the {@code predicate} function and waits for {@code predicate(fileChooser)} to return a truthy value. Will throw an error if
   * the page is closed before the file chooser is opened.
   *
   * @param callback Callback that performs the action triggering the event.
   */
  FileChooser waitForFileChooser(WaitForFileChooserOptions options, Runnable callback);
  /**
   * Returns when the {@code expression} returns a truthy value. It resolves to a JSHandle of the truthy value.
   *
   * <p> The [{@code method: Page.waitForFunction}] can be used to observe viewport size change:
   *
   * <p> To pass an argument to the predicate of [{@code method: Page.waitForFunction}] function:
   *
   * <p> Shortcut for main frame's [{@code method: Frame.waitForFunction}].
   *
   * @param expression JavaScript expression to be evaluated in the browser context. If it looks like a function declaration, it is interpreted
   * as a function. Otherwise, evaluated as an expression.
   * @param arg Optional argument to pass to {@code expression}.
   */
  default JSHandle waitForFunction(String expression, Object arg) {
    return waitForFunction(expression, arg, null);
  }
  /**
   * Returns when the {@code expression} returns a truthy value. It resolves to a JSHandle of the truthy value.
   *
   * <p> The [{@code method: Page.waitForFunction}] can be used to observe viewport size change:
   *
   * <p> To pass an argument to the predicate of [{@code method: Page.waitForFunction}] function:
   *
   * <p> Shortcut for main frame's [{@code method: Frame.waitForFunction}].
   *
   * @param expression JavaScript expression to be evaluated in the browser context. If it looks like a function declaration, it is interpreted
   * as a function. Otherwise, evaluated as an expression.
   */
  default JSHandle waitForFunction(String expression) {
    return waitForFunction(expression, null);
  }
  /**
   * Returns when the {@code expression} returns a truthy value. It resolves to a JSHandle of the truthy value.
   *
   * <p> The [{@code method: Page.waitForFunction}] can be used to observe viewport size change:
   *
   * <p> To pass an argument to the predicate of [{@code method: Page.waitForFunction}] function:
   *
   * <p> Shortcut for main frame's [{@code method: Frame.waitForFunction}].
   *
   * @param expression JavaScript expression to be evaluated in the browser context. If it looks like a function declaration, it is interpreted
   * as a function. Otherwise, evaluated as an expression.
   * @param arg Optional argument to pass to {@code expression}.
   */
  JSHandle waitForFunction(String expression, Object arg, WaitForFunctionOptions options);
  /**
   * Returns when the required load state has been reached.
   *
   * <p> This resolves when the page reaches a required load state, {@code load} by default. The navigation must have been committed
   * when this method is called. If current document has already reached the required state, resolves immediately.
   *
   * <p> Shortcut for main frame's [{@code method: Frame.waitForLoadState}].
   *
   * @param state Optional load state to wait for, defaults to {@code load}. If the state has been already reached while loading current
   * document, the method resolves immediately. Can be one of:
   * - {@code 'load'} - wait for the {@code load} event to be fired.
   * - {@code 'domcontentloaded'} - wait for the {@code DOMContentLoaded} event to be fired.
   * - {@code 'networkidle'} - wait until there are no network connections for at least {@code 500} ms.
   */
  default void waitForLoadState(LoadState state) {
    waitForLoadState(state, null);
  }
  /**
   * Returns when the required load state has been reached.
   *
   * <p> This resolves when the page reaches a required load state, {@code load} by default. The navigation must have been committed
   * when this method is called. If current document has already reached the required state, resolves immediately.
   *
   * <p> Shortcut for main frame's [{@code method: Frame.waitForLoadState}].
   */
  default void waitForLoadState() {
    waitForLoadState(null);
  }
  /**
   * Returns when the required load state has been reached.
   *
   * <p> This resolves when the page reaches a required load state, {@code load} by default. The navigation must have been committed
   * when this method is called. If current document has already reached the required state, resolves immediately.
   *
   * <p> Shortcut for main frame's [{@code method: Frame.waitForLoadState}].
   *
   * @param state Optional load state to wait for, defaults to {@code load}. If the state has been already reached while loading current
   * document, the method resolves immediately. Can be one of:
   * - {@code 'load'} - wait for the {@code load} event to be fired.
   * - {@code 'domcontentloaded'} - wait for the {@code DOMContentLoaded} event to be fired.
   * - {@code 'networkidle'} - wait until there are no network connections for at least {@code 500} ms.
   */
  void waitForLoadState(LoadState state, WaitForLoadStateOptions options);
  /**
   * Waits for the main frame navigation and returns the main resource response. In case of multiple redirects, the
   * navigation will resolve with the response of the last redirect. In case of navigation to a different anchor or
   * navigation due to History API usage, the navigation will resolve with {@code null}.
   *
   * <p> This resolves when the page navigates to a new URL or reloads. It is useful for when you run code which will indirectly
   * cause the page to navigate. e.g. The click target has an {@code onclick} handler that triggers navigation from a {@code setTimeout}.
   * Consider this example:
   *
   * <p> <strong>NOTE:</strong> Usage of the [History API](https://developer.mozilla.org/en-US/docs/Web/API/History_API) to change the URL is
   * considered a navigation.
   *
   * <p> Shortcut for main frame's [{@code method: Frame.waitForNavigation}].
   *
   * @param callback Callback that performs the action triggering the event.
   */
  default Response waitForNavigation(Runnable callback) {
    return waitForNavigation(null, callback);
  }
  /**
   * Waits for the main frame navigation and returns the main resource response. In case of multiple redirects, the
   * navigation will resolve with the response of the last redirect. In case of navigation to a different anchor or
   * navigation due to History API usage, the navigation will resolve with {@code null}.
   *
   * <p> This resolves when the page navigates to a new URL or reloads. It is useful for when you run code which will indirectly
   * cause the page to navigate. e.g. The click target has an {@code onclick} handler that triggers navigation from a {@code setTimeout}.
   * Consider this example:
   *
   * <p> <strong>NOTE:</strong> Usage of the [History API](https://developer.mozilla.org/en-US/docs/Web/API/History_API) to change the URL is
   * considered a navigation.
   *
   * <p> Shortcut for main frame's [{@code method: Frame.waitForNavigation}].
   *
   * @param callback Callback that performs the action triggering the event.
   */
  Response waitForNavigation(WaitForNavigationOptions options, Runnable callback);
  /**
   * Performs action and waits for a popup {@code Page}. If predicate is provided, it passes [Popup] value into the {@code predicate}
   * function and waits for {@code predicate(page)} to return a truthy value. Will throw an error if the page is closed before the
   * popup event is fired.
   *
   * @param callback Callback that performs the action triggering the event.
   */
  default Page waitForPopup(Runnable callback) {
    return waitForPopup(null, callback);
  }
  /**
   * Performs action and waits for a popup {@code Page}. If predicate is provided, it passes [Popup] value into the {@code predicate}
   * function and waits for {@code predicate(page)} to return a truthy value. Will throw an error if the page is closed before the
   * popup event is fired.
   *
   * @param callback Callback that performs the action triggering the event.
   */
  Page waitForPopup(WaitForPopupOptions options, Runnable callback);
  /**
   * Waits for the matching request and returns it.
   *
   *
   * @param urlOrPredicate Request URL string, regex or predicate receiving {@code Request} object.
   * @param callback Callback that performs the action triggering the event.
   */
  default Request waitForRequest(String urlOrPredicate, Runnable callback) {
    return waitForRequest(urlOrPredicate, null, callback);
  }
  /**
   * Waits for the matching request and returns it.
   *
   *
   * @param urlOrPredicate Request URL string, regex or predicate receiving {@code Request} object.
   * @param callback Callback that performs the action triggering the event.
   */
  Request waitForRequest(String urlOrPredicate, WaitForRequestOptions options, Runnable callback);
  /**
   * Waits for the matching request and returns it.
   *
   *
   * @param urlOrPredicate Request URL string, regex or predicate receiving {@code Request} object.
   * @param callback Callback that performs the action triggering the event.
   */
  default Request waitForRequest(Pattern urlOrPredicate, Runnable callback) {
    return waitForRequest(urlOrPredicate, null, callback);
  }
  /**
   * Waits for the matching request and returns it.
   *
   *
   * @param urlOrPredicate Request URL string, regex or predicate receiving {@code Request} object.
   * @param callback Callback that performs the action triggering the event.
   */
  Request waitForRequest(Pattern urlOrPredicate, WaitForRequestOptions options, Runnable callback);
  /**
   * Waits for the matching request and returns it.
   *
   *
   * @param urlOrPredicate Request URL string, regex or predicate receiving {@code Request} object.
   * @param callback Callback that performs the action triggering the event.
   */
  default Request waitForRequest(Predicate<Request> urlOrPredicate, Runnable callback) {
    return waitForRequest(urlOrPredicate, null, callback);
  }
  /**
   * Waits for the matching request and returns it.
   *
   *
   * @param urlOrPredicate Request URL string, regex or predicate receiving {@code Request} object.
   * @param callback Callback that performs the action triggering the event.
   */
  Request waitForRequest(Predicate<Request> urlOrPredicate, WaitForRequestOptions options, Runnable callback);
  /**
   * Returns the matched response.
   *
   *
   * @param urlOrPredicate Request URL string, regex or predicate receiving {@code Response} object.
   * @param callback Callback that performs the action triggering the event.
   */
  default Response waitForResponse(String urlOrPredicate, Runnable callback) {
    return waitForResponse(urlOrPredicate, null, callback);
  }
  /**
   * Returns the matched response.
   *
   *
   * @param urlOrPredicate Request URL string, regex or predicate receiving {@code Response} object.
   * @param callback Callback that performs the action triggering the event.
   */
  Response waitForResponse(String urlOrPredicate, WaitForResponseOptions options, Runnable callback);
  /**
   * Returns the matched response.
   *
   *
   * @param urlOrPredicate Request URL string, regex or predicate receiving {@code Response} object.
   * @param callback Callback that performs the action triggering the event.
   */
  default Response waitForResponse(Pattern urlOrPredicate, Runnable callback) {
    return waitForResponse(urlOrPredicate, null, callback);
  }
  /**
   * Returns the matched response.
   *
   *
   * @param urlOrPredicate Request URL string, regex or predicate receiving {@code Response} object.
   * @param callback Callback that performs the action triggering the event.
   */
  Response waitForResponse(Pattern urlOrPredicate, WaitForResponseOptions options, Runnable callback);
  /**
   * Returns the matched response.
   *
   *
   * @param urlOrPredicate Request URL string, regex or predicate receiving {@code Response} object.
   * @param callback Callback that performs the action triggering the event.
   */
  default Response waitForResponse(Predicate<Response> urlOrPredicate, Runnable callback) {
    return waitForResponse(urlOrPredicate, null, callback);
  }
  /**
   * Returns the matched response.
   *
   *
   * @param urlOrPredicate Request URL string, regex or predicate receiving {@code Response} object.
   * @param callback Callback that performs the action triggering the event.
   */
  Response waitForResponse(Predicate<Response> urlOrPredicate, WaitForResponseOptions options, Runnable callback);
  /**
   * Returns when element specified by selector satisfies {@code state} option. Returns {@code null} if waiting for {@code hidden} or
   * {@code detached}.
   *
   * <p> Wait for the {@code selector} to satisfy {@code state} option (either appear/disappear from dom, or become visible/hidden). If at
   * the moment of calling the method {@code selector} already satisfies the condition, the method will return immediately. If the
   * selector doesn't satisfy the condition for the {@code timeout} milliseconds, the function will throw.
   *
   * <p> This method works across navigations:
   *
   *
   * @param selector A selector to query for. See [working with selectors](./selectors.md) for more details.
   */
  default ElementHandle waitForSelector(String selector) {
    return waitForSelector(selector, null);
  }
  /**
   * Returns when element specified by selector satisfies {@code state} option. Returns {@code null} if waiting for {@code hidden} or
   * {@code detached}.
   *
   * <p> Wait for the {@code selector} to satisfy {@code state} option (either appear/disappear from dom, or become visible/hidden). If at
   * the moment of calling the method {@code selector} already satisfies the condition, the method will return immediately. If the
   * selector doesn't satisfy the condition for the {@code timeout} milliseconds, the function will throw.
   *
   * <p> This method works across navigations:
   *
   *
   * @param selector A selector to query for. See [working with selectors](./selectors.md) for more details.
   */
  ElementHandle waitForSelector(String selector, WaitForSelectorOptions options);
  /**
   * Waits for the given {@code timeout} in milliseconds.
   *
   * <p> Note that {@code page.waitForTimeout()} should only be used for debugging. Tests using the timer in production are going to be
   * flaky. Use signals such as network events, selectors becoming visible and others instead.
   *
   * <p> Shortcut for main frame's [{@code method: Frame.waitForTimeout}].
   *
   * @param timeout A timeout to wait for
   */
  void waitForTimeout(double timeout);
  /**
   * Performs action and waits for a new {@code WebSocket}. If predicate is provided, it passes {@code WebSocket} value into the
   * {@code predicate} function and waits for {@code predicate(webSocket)} to return a truthy value. Will throw an error if the page is
   * closed before the WebSocket event is fired.
   *
   * @param callback Callback that performs the action triggering the event.
   */
  default WebSocket waitForWebSocket(Runnable callback) {
    return waitForWebSocket(null, callback);
  }
  /**
   * Performs action and waits for a new {@code WebSocket}. If predicate is provided, it passes {@code WebSocket} value into the
   * {@code predicate} function and waits for {@code predicate(webSocket)} to return a truthy value. Will throw an error if the page is
   * closed before the WebSocket event is fired.
   *
   * @param callback Callback that performs the action triggering the event.
   */
  WebSocket waitForWebSocket(WaitForWebSocketOptions options, Runnable callback);
  /**
   * Performs action and waits for a new {@code Worker}. If predicate is provided, it passes {@code Worker} value into the {@code predicate}
   * function and waits for {@code predicate(worker)} to return a truthy value. Will throw an error if the page is closed before
   * the worker event is fired.
   *
   * @param callback Callback that performs the action triggering the event.
   */
  default Worker waitForWorker(Runnable callback) {
    return waitForWorker(null, callback);
  }
  /**
   * Performs action and waits for a new {@code Worker}. If predicate is provided, it passes {@code Worker} value into the {@code predicate}
   * function and waits for {@code predicate(worker)} to return a truthy value. Will throw an error if the page is closed before
   * the worker event is fired.
   *
   * @param callback Callback that performs the action triggering the event.
   */
  Worker waitForWorker(WaitForWorkerOptions options, Runnable callback);
  /**
   * This method returns all of the dedicated [WebWorkers](https://developer.mozilla.org/en-US/docs/Web/API/Web_Workers_API)
   * associated with the page.
   *
   * <p> <strong>NOTE:</strong> This does not contain ServiceWorkers
   */
  List<Worker> workers();
}

